package com.example.realestate.Controller;

import com.example.realestate.Model.Property;
import com.example.realestate.Model.User;
import com.example.realestate.Model.Package;
import com.example.realestate.Service.CommonService;
import com.example.realestate.Service.PackageService;
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

    @Autowired
    private PackageService packageService;

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

    @PostMapping(value = "/search")
    public String search(Model model,
                         @RequestParam(value = "city", required = false) String city,
                         @RequestParam(value = "propertyType", required = false) String propertyType,
                         @RequestParam(value = "bedrooms", required = false) String bedrooms) {
        List<Property> searchedList = propertyService.getSearchedProperties(city, propertyType, Integer.valueOf(bedrooms));
        model.addAttribute("publicPost", searchedList);
        return "listings";
    }

    @GetMapping(value = "/search")
    public String searchPage(){
        return "search";
    }

    @PostMapping(value = "/filtered")
    public String filteredSearch(Model model,
                                 @RequestParam(value = "city", required = false) String city,
                                 @RequestParam(value = "serviceType", required = false) String serviceType,
                                 @RequestParam(value = "propertyType", required = false) String propertyType,
                                 @RequestParam(value = "bedrooms", required = false) String bedrooms,
                                 @RequestParam(value = "minimum", required = false) String minimumPrice,
                                 @RequestParam(value = "maximum", required = false) String maximumPrice,
                                 @RequestParam(value = "status", required = false) String status){

        return "listings";
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

    @GetMapping(value = "/list_search")
    public String list_search(Model model, @RequestParam(value = "category", required = false) String category, @RequestParam(value = "serviceType", required = false) String serviceType, @RequestParam(value = "status", required = false) String status) {
        // Retrieve the filtered properties based on category, serviceType or status
        List<Property> filteredProperties = propertyService.getFilteredProperties(category, serviceType, status);
        model.addAttribute("publicPost", filteredProperties);
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

    @GetMapping(value = "/service_detail")
    public String service_detail(Model model) {
        List<Package> packageList = packageService.getAllPackages();
        model.addAttribute("packageList", packageList);
        return "buy_service";
    }

    @ModelAttribute
    public void getUserInfo(Principal p, Model model) {
        if (p != null) {
            String username = p.getName();
            User curUser = userService.getUserByEmail(username);
            model.addAttribute("user", Objects.requireNonNullElseGet(curUser, User::new));
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
