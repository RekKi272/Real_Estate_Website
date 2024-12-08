package com.example.realestate.Controller;

import com.example.realestate.Model.Package;
import com.example.realestate.Model.User;
import com.example.realestate.Model.User_Package;
import com.example.realestate.Service.PackageService;
import com.example.realestate.Service.PaypalService;
import com.example.realestate.Service.UserService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PaypalController {

    @Autowired
    private PaypalService paypalService;

    @Autowired
    private UserService userService;

    @Autowired
    private PackageService packageService;


    @ModelAttribute
    public void getUserInfo(Principal p, Model model) {
        if (p != null) {
            String username = p.getName();
            User curUser = userService.getUserByEmail(username);
            model.addAttribute("user", Objects.requireNonNullElseGet(curUser, User::new));
        }
        else {
            model.addAttribute("user", null);
        }
    }

    @PostMapping("/payment/create")
    public RedirectView createPaypal(
            @RequestParam("method") String method,
            @RequestParam("amount") String amount,
            @RequestParam("currency") String currency,
            @RequestParam("description") String description,
            @RequestParam("packageId") Long packageId
    ){
        try{
            String cancelUrl = "http://localhost:8080/payment/cancel";
            String successUrl = "http://localhost:8080/payment/success?packageId=" + packageId;
            Payment payment = paypalService.createPayment(
                    Double.valueOf(amount),
                    currency,
                    method,
                    "sale",
                    description,
                    cancelUrl,
                    successUrl
            );

            // Log the details of the payment to debug
//            System.out.println(payment);

            for(Links links: payment.getLinks()){
                log.info("Link: {}", links.getHref());
                if(links.getRel().equals("approval_url")){
                    return new RedirectView(links.getHref());
                }
            }
        } catch (PayPalRESTException e){
            log.error("Error occurred during PayPal payment creation:", e);
        }
        return new RedirectView("/payment/error");
    }

    @GetMapping(value = "/payment/success")
    public String paymentSuccess(@RequestParam("paymentId") String paymentId,
                                 @RequestParam("token") String token,
                                 @RequestParam("PayerID") String payerId,
                                 @RequestParam("packageId") Long packageId,
                                 Principal principal){
        User curUser = userService.getUserByEmail(principal.getName());
        try{
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){

                Package selectedPackage = packageService.getPackageById(packageId);

                // Create and save new User_Package
                User_Package userPackage = new User_Package();
                userPackage.setUser(curUser);
                userPackage.setAPackage(selectedPackage);
                userPackage.setStartDate(LocalDateTime.now());
                userPackage.setEndDate(LocalDateTime.now().plusDays(30L *selectedPackage.getDuration()));

                userService.saveUserPackage(userPackage);

                curUser.setCheckStatus(true);
                userService.updateUser(curUser);

                return "payment_success";
            }
        } catch (PayPalRESTException e){
            log.error("Error occurred::", e);
        }
        return "payment_success";
    }

    @GetMapping(value = "/payment/cancel")
    public String paymentCancel(){
        return "payment_cancel";
    }

    @GetMapping(value = "/payment/error")
    public String paymentError(){
        return "payment_error";
    }

}
