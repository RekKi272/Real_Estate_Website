package com.example.realestate.Controller;

import com.example.realestate.Model.Image;
import com.example.realestate.Model.Package;
import com.example.realestate.Model.Property;
import com.example.realestate.Model.User;
import com.example.realestate.Service.PackageService;
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
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PropertyService propertyService;

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

    @GetMapping(value = "/")
    public String index() {
        return "admin/index";
    }

    @GetMapping(value = "/users")
    public String editUser(Model model, @RequestParam Integer type) {
        List<User> userList = null;
        if(type == 1){
            userList = userService.getUserByRole("ROLE_USER");
        }
        else {
            userList = userService.getUserByRole("ROLE_ADMIN");
        }
        model.addAttribute("userList", userList);
        model.addAttribute("totalUser", userList.size());
        model.addAttribute("userType", type);
        return "/admin/edit_user";
    }

    @GetMapping("/updateSts")
    public String updateUserAccountStatus(@RequestParam Boolean status, @RequestParam Long id, @RequestParam Integer type, HttpSession session) {
        Boolean updated = userService.updateUserAccess(id, status);
        if(updated){
            session.setAttribute("successMsg", "Account Status Updated Successfully");
        }
        else {
            session.setAttribute("errorMsg", "Account Status Update Failed");
        }

        return "redirect:/admin/users?type=" + type;
    }

    @GetMapping(value ="/add_admin")
    public String addAdmin(Model model) {
        return "admin/add_admin";
    }

    @GetMapping(value = "/posts")
    public String postListing(Model model) {
        List<Property> propertyList = propertyService.getAllProperty();
        model.addAttribute("propertyList", propertyList);
        return "admin/post_management";
    }

    @GetMapping(value = "/view_property")
    public String view_property(Model model, @RequestParam Long pid) {
        Property property = propertyService.getPropertyById(pid);
        model.addAttribute("post", property);
        return "/admin/post_detail";
    }

    @GetMapping(value = "/edit_property")
    public String view_edit_my_property(Model model, @RequestParam("pid") Long pid) {
        Property property = propertyService.getPropertyById(pid);
        model.addAttribute("property", property);
        return "admin/edit_post";
    }

    @PostMapping(value = "/saveAdmin")
    public String saveAdmin(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword,HttpSession session) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);

        if(userService.isEmailExist(user.getEmail())) {
            session.setAttribute("errorMsg", "Email Already Exist");
            return "redirect:/admin/add_admin";
        }

        if(!confirmPassword.equals(user.getPassword())) {
            session.setAttribute("errorMsg", "Password Does Not Match");
            return "redirect:/admin/add_admin";
        }

        userService.saveAdmin(user);
        session.setAttribute("successMsg", "Registration Successful");
        return "redirect:/admin/add_admin";
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
            curProperty.setIsPublic(property.getIsPublic() != null ? property.getIsPublic() : false);
            // Update images if provided.
            if (images != null && images.length > 0) {
                List<Image> newImages = propertyService.saveImages(curProperty, images);
                curProperty.setImages(newImages);
            }

            // Save the updated property.
            propertyService.save(curProperty);

            session.setAttribute("successMsg", "Property updated successfully!");
            return "redirect:/admin/view_property?pid=" + curProperty.getId();

        } catch (Exception e) {
            session.setAttribute("errorMsg", "Error updating property: " + e.getMessage());
            return "redirect:/admin/view_property?pid=" + property.getId();
        }
    }

    @GetMapping(value = "/view_package")
    public String view_package(Model model) {
        List<Package> packageList = packageService.getAllPackages();
        model.addAttribute("packageList", packageList);
        return "/admin/view_package_service";
    }

    @GetMapping(value = "editPackage")
    public String editPackage(@RequestParam("id") Long id, Model model) {
        Package curPackage = packageService.getPackageById(id);
        model.addAttribute("package", curPackage);
        return "admin/edit_package";
    }

    @PostMapping(value = "editPackage")
    public String editPackage(@ModelAttribute Package pack, HttpSession session) {
        Package curPackage = packageService.getPackageById(pack.getId());
        curPackage.setName(pack.getName());
        curPackage.setPrice(pack.getPrice());
        curPackage.setDescription(pack.getDescription());
        curPackage.setDuration(pack.getDuration());
        packageService.save(curPackage);
        session.setAttribute("successMsg", "Package updated successfully!");
        return "redirect:/admin/view_package";
    }

    @PostMapping(value = "/addPackage")
    public String savePackage(@ModelAttribute Package pack, HttpSession session){
        packageService.save(pack);
        session.setAttribute("successMsg", "Package added successfully!");
        return "redirect:/admin/view_package";
    }

    @GetMapping(value = "/addPackage")
    public String addPackage() {
        return "admin/add_package_service";
    }

}
