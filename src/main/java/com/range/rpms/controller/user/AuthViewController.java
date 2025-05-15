package com.range.rpms.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthViewController {

    @GetMapping("/login")
    public String login() {

        return "login";

    }
    @GetMapping("/register")
    public String register() {

        return "register";

    }




}
