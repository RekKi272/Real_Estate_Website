package com.example.realestate.Controller;

import com.example.realestate.Model.User;
import com.example.realestate.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    UserService userService;

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

    @PostMapping(value = "/saveAdmin")
    public String saveAdmin(@ModelAttribute User user, @RequestParam("confirmPassword") String confirmPassword,HttpSession session) {

        if(user == null) {
            session.setAttribute("errorMsg", "Please enter a user name");
            return "redirect:/admin/add_admin";
        }

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
}
