package com.range.rpms.packages.controller.admin;

import com.range.rpms.common.dto.GenericResponse;
import com.range.rpms.packages.api.admin.AdminPackageApi;
import com.range.rpms.packages.exception.PackageNotFoundException;
import com.range.rpms.packages.service.AdminPackageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AdminController implements AdminPackageApi {
    private final AdminPackageService adminPackageService;
    public AdminController(AdminPackageService adminPackageService) {
        this.adminPackageService = adminPackageService;
    }

    public ResponseEntity<GenericResponse<Void>> deletePackage(@PathVariable String id) throws PackageNotFoundException {

        adminPackageService.deleteOtherUserPackage(id);
        return  ResponseEntity.ok(new GenericResponse<>(
                true,
                "package deleted successfully",
                HttpStatus.OK.value(),
                null
        ));
    }


    public ResponseEntity<GenericResponse<Void>> deleteAllPackages() throws PackageNotFoundException {
        adminPackageService.deleteAllPackages();
        return  ResponseEntity.ok(new GenericResponse<>(
                        true,
                        "all packages deleted successfully",
                        HttpStatus.OK.value(),
                        null
                )
        );
    }





}
