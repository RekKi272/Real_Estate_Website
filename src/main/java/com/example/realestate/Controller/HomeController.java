package com.example.realestate.Controller;

import com.example.realestate.Model.Property;
import com.example.realestate.Model.User;
import com.example.realestate.Service.CommonService;
import com.example.realestate.Service.PropertyService;
import com.example.realestate.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private PropertyService propertyService;

    @GetMapping(value = "/")
    public String home(Model model) {
        List<Property> publicPost = propertyService.latestPublicProperty();
        // Get the first three properties, if available
        List<Property> topThree = publicPost.stream()
                .limit(3)
                .toList();
        model.addAttribute("publicPost", topThree);
        return "index";
    }

    @GetMapping(value = "/search")
    public String search() {
        return "search";
    }

    @GetMapping(value = "/about")
    public String about() {
        return "about";
    }

    @GetMapping(value = "/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping(value = "/listing")
    public String listing(Model model) {
        List<Property> publicPost = propertyService.latestPublicProperty();
        model.addAttribute("publicPost", publicPost);
        return "listings";
    }

    @GetMapping(value ="/signin")
    public String signin() {
        return "login";
    }

    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }

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

    @GetMapping(value = "/view_property")
    public String viewProperty(Model model, @RequestParam Long pid) {
        Property property = propertyService.getPropertyById(pid);
        model.addAttribute("post", property);
        return "view_property";
    }

    @PostMapping(value = "/saveUser")
    public String register(@ModelAttribute User user, @RequestParam("confirmPassword") String confirmPassword, Model model, HttpSession session) {

        if(user == null) {
            session.setAttribute("errorMsg", "Please enter a user name");
            return "redirect:/register";
        }

        if(userService.isEmailExist(user.getEmail())) {
            session.setAttribute("errorMsg", "Email Already Exist");
            return "redirect:/register";
        }

        if(!confirmPassword.equals(user.getPassword())) {
            session.setAttribute("errorMsg", "Password Does Not Match");
            return "redirect:/register";
        }

        userService.saveUser(user);
        session.setAttribute("successMsg", "Registration Successful");
        return "redirect:/signin";
    }
}
