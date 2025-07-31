package com.range.rpms.packages.api.user;

import com.range.rpms.common.dto.GenericResponse;
import com.range.rpms.packages.dto.PackageMetaData;
import com.range.rpms.packages.dto.UploadPackageRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.io.IOException;

@RequestMapping("/${app.base-path}/packages/user")
public interface PackageApi {

    @Operation(summary = "Download package by ID")
    @GetMapping("/{packageId}")
    ResponseEntity<ByteArrayResource> downloadPackage(@PathVariable String packageId);


    @Operation(summary = "Upload new package")
    @PostMapping("/upload")
    ResponseEntity<GenericResponse<PackageMetaData>> uploadPackage(
            @ModelAttribute @Valid UploadPackageRequest uploadPackageRequest
    ) throws IOException;


}
