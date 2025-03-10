package com.range.rpms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PackageControllerView {
    @GetMapping("/admin-panel")
    public String AdminPanel(Model model) {



        return "admin-panel";
    }
}
