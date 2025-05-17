package com.range.rpms.packages.controller.user;

import com.range.rpms.common.dto.GenericResponse;
import com.range.rpms.packages.api.user.PackageApi;
import com.range.rpms.packages.dto.PackageMetaData;
import com.range.rpms.packages.dto.UploadPackageRequest;
import com.range.rpms.packages.exception.PackageNotFoundException;
import com.range.rpms.packages.service.PackageService;
import jakarta.validation.Valid;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController

public class PackageController implements PackageApi {
    private final PackageService packageService;


    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }
    public ResponseEntity<ByteArrayResource> downloadPackage(@PathVariable String packageId) throws PackageNotFoundException {

        ByteArrayResource resource = packageService.downloadPackageById(packageId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + packageId + "\"")
                .body(resource);
    }

    public ResponseEntity<GenericResponse<PackageMetaData>> uploadPackage(@ModelAttribute @Valid UploadPackageRequest uploadPackageRequest)
            throws PackageNotFoundException, IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new GenericResponse<>(
                        true,
                        "File uploaded successfully",
                        HttpStatus.CREATED.value(),
                        packageService.addPackage(uploadPackageRequest)
                )
        );
    }


}
