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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private CommonService commonService;

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

    @GetMapping(value = "/post_property")
    public String post_property() {
        return "user/post_property";
    }

    @GetMapping(value = "/my_posts")
    public String my_posts(Model model, Principal p) {
        if(p != null) {
            String username = p.getName();
            User curUser = userService.getUserByEmail(username);
            char firstChar = curUser.getName().charAt(0);
            model.addAttribute("userChar", firstChar);
            model.addAttribute("user", Objects.requireNonNullElseGet(curUser, null));
            List<Property> propertyList = propertyService.getListPropertyByUser(curUser);
            model.addAttribute("myPostList", !propertyList.isEmpty() ? propertyList : null);
        }
        return "user/my_posts";
    }

    @PostMapping(value = "/postProperty")
    public String saveProperty(@ModelAttribute Property property, @RequestParam("files") MultipartFile[] files, Model model, HttpSession session, Principal p) {

        // Handle the checkbox values - if null, set them to false
        property.setHasLift(property.getHasLift() != null ? property.getHasLift() : false);
        property.setHasPlayground(property.getHasPlayground() != null ? property.getHasPlayground() : false);
        property.setHasGarden(property.getHasGarden() != null ? property.getHasGarden() : false);
        property.setHasParkingArea(property.getHasParkingArea() != null ? property.getHasParkingArea() : false);
        property.setHasShoppingMall(property.getHasShoppingMall() != null ? property.getHasShoppingMall() : false);
        property.setHasHospital(property.getHasHospital() != null ? property.getHasHospital() : false);
        property.setHasSchool(property.getHasSchool() != null ? property.getHasSchool() : false);
        property.setIsPublic(Boolean.FALSE);
        if(p != null) {
            String username = p.getName();
            User curUser = userService.getUserByEmail(username);
            property.setPostedBy(curUser);
            try{
                propertyService.saveProperty(property, files);
                String path = "src/main/resources/static/images/";
                for (MultipartFile file : files) {
                    commonService.saveImage(path, file);
                }
                session.setAttribute("successMsg", "Property posted successfully, please wait for approval");
            } catch (Exception e) {
                session.setAttribute("errorMsg", "Error while saving property: " + e.getMessage());
            }
        }
        return "redirect:/user/post_property";
    }

}
