package com.range.rpms.packages.controller.user;

import com.range.rpms.common.dto.GenericResponse;
import com.range.rpms.packages.api.user.PackageMetaDataApi;
import com.range.rpms.packages.dto.PackageMetaData;
import com.range.rpms.packages.service.PackageMetaDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping(value = "info")
public class PackageMetaDataController implements PackageMetaDataApi {
    private final PackageMetaDataService packageMetaDataService;
    public PackageMetaDataController(PackageMetaDataService packageMetaDataService) {
        this.packageMetaDataService = packageMetaDataService;
    }
        @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<PackageMetaData>>> getAllPackages() {
        return ResponseEntity.ok(new GenericResponse<>(true,
                "all packages fetched",
                200,
                packageMetaDataService.getAllPackages()
        ));

    }


    public ResponseEntity<GenericResponse<List<PackageMetaData>>> searchPackage(@PathVariable String packageName){

        return ResponseEntity.ok(new
                GenericResponse<>(true,
                "matched packages",
                HttpStatus.OK.value(),
                packageMetaDataService.searchPackage(packageName)));

    }



}
