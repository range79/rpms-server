package com.range.rpms.controller;

import com.range.rpms.dto.PackageDto;
import com.range.rpms.service.PackageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PackageControllerView {
    private final PackageService packageService;
    public PackageControllerView(PackageService packageService) {
            this.packageService = packageService;
    }
    @GetMapping("/admin-panel")
    public String AdminPanel(Model model) {
        List<PackageDto> allPackages = packageService.getAllPackages();
        model.addAttribute("packages", allPackages);
        return "admin-panel";


}}
