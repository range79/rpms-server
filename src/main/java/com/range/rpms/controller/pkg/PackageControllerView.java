package com.range.rpms.controller.pkg;

import com.range.rpms.dto.pkg.PackageMetaData;
import com.range.rpms.service.PackageMetaDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PackageControllerView {
    private final PackageMetaDataService packageMetaDataService;
    public PackageControllerView(PackageMetaDataService packageMetaDataService) {

        this.packageMetaDataService = packageMetaDataService;
    }
    @GetMapping("/")
    public String adminPanel(Model model) {

        List<PackageMetaData> allPackages = packageMetaDataService.getAllPackages();

        model.addAttribute("packages", allPackages);

        return "admin-panel";

    }




}
