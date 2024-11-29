package com.example.realestate.Controller;

import com.example.realestate.Model.User;
import com.example.realestate.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    UserService userService;

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
}
