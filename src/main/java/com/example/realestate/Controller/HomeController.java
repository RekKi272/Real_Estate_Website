package com.example.realestate.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String home() {
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
    public String listing() {
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

    @PostMapping(value ="/signin")
    public String signin(Model model) {
        return "index";
    }
}
