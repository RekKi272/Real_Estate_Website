package com.example.realestate.Controller;

import com.example.realestate.Model.Image;
import com.example.realestate.Model.Property;
import com.example.realestate.Model.User;
import com.example.realestate.Service.CommonService;
import com.example.realestate.Service.PropertyService;
import com.example.realestate.Service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
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
            model.addAttribute("user", new User());
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

    @GetMapping(value = "/view_my_property")
    public String view_my_property(Model model, @RequestParam("pid") Long pid) {
        Property property = propertyService.getPropertyById(pid);
        model.addAttribute("post", property);
        return "user/my_property_detail";
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

    @GetMapping(value = "/edit_property")
    public String view_edit_my_property(Model model, @RequestParam("pid") Long pid) {
        Property property = propertyService.getPropertyById(pid);
        model.addAttribute("property", property);
        return "user/edit_my_property";
    }

    @PostMapping(value = "/edit_property")
    public String edit_property(@ModelAttribute Property property,
                                @RequestParam(value = "images", required = false) MultipartFile[] images ,
                                HttpSession session) {
        try {
            // Fetch the existing property from the database.
            Property curProperty = propertyService.getPropertyById(property.getId());

            // Update property fields only if provided.
            curProperty.setTitle(property.getTitle() != null ? property.getTitle() : curProperty.getTitle());
            curProperty.setAddress(property.getAddress() != null ? property.getAddress() : curProperty.getAddress());
            curProperty.setDescription(property.getDescription() != null ? property.getDescription() : curProperty.getDescription());
            curProperty.setPropertyType(property.getPropertyType() != null ? property.getPropertyType() : curProperty.getPropertyType());
            curProperty.setServiceType(property.getServiceType() != null ? property.getServiceType() : curProperty.getServiceType());
            curProperty.setCity(property.getCity() != null ? property.getCity() : curProperty.getCity());
            curProperty.setCountry(property.getCountry() != null ? property.getCountry() : curProperty.getCountry());
            curProperty.setPrice(property.getPrice() != null ? property.getPrice() : curProperty.getPrice());
            curProperty.setBedrooms(property.getBedrooms() != null ? property.getBedrooms() : curProperty.getBedrooms());
            curProperty.setBathrooms(property.getBathrooms() != null ? property.getBathrooms() : curProperty.getBathrooms());
            curProperty.setFloors(property.getFloors() != null ? property.getFloors() : curProperty.getFloors());
            curProperty.setBalcony(property.getBalcony() != null ? property.getBalcony() : curProperty.getBalcony());
            curProperty.setSize(property.getSize() != null ? property.getSize() : curProperty.getSize());
            curProperty.setStatus(property.getStatus() != null ? property.getStatus() : curProperty.getStatus());

            // Update amenities with defaults to false.
            curProperty.setHasLift(property.getHasLift() != null ? property.getHasLift() : false);
            curProperty.setHasPlayground(property.getHasPlayground() != null ? property.getHasPlayground() : false);
            curProperty.setHasGarden(property.getHasGarden() != null ? property.getHasGarden() : false);
            curProperty.setHasParkingArea(property.getHasParkingArea() != null ? property.getHasParkingArea() : false);
            curProperty.setHasShoppingMall(property.getHasShoppingMall() != null ? property.getHasShoppingMall() : false);
            curProperty.setHasHospital(property.getHasHospital() != null ? property.getHasHospital() : false);
            curProperty.setHasSchool(property.getHasSchool() != null ? property.getHasSchool() : false);

            // Update images if provided.
            if (images != null && images.length > 0) {
                List<Image> newImages = propertyService.saveImages(curProperty, images);
                curProperty.setImages(newImages);
            }

            // Save the updated property.
            propertyService.save(curProperty);

            session.setAttribute("successMsg", "Property updated successfully!");
            return "redirect:/user/view_my_property?pid=" + curProperty.getId();

        } catch (Exception e) {
            session.setAttribute("errorMsg", "Error updating property: " + e.getMessage());
            return "redirect:/user/view_my_property?pid=" + property.getId();
        }
    }

    @GetMapping(value = "/delete")
    public String deleteProperties(@RequestParam(value = "id") Long id,Principal p){
        if(p != null) {
            Property property = propertyService.getPropertyById(id);
            propertyService.delete(property);
        }
        return "redirect:/user/my_posts";
    }

}