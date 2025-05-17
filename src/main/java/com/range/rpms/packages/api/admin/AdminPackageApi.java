package com.range.rpms.packages.api.admin;

import com.range.rpms.common.dto.GenericResponse;
import com.range.rpms.packages.exception.PackageNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Admin Package API", description = "Admin operations for managing packages")
@RequestMapping("/admin/packages")
public interface AdminPackageApi {

    @Operation(
            summary = "Delete a package by ID",
            description = "Deletes a specific package using its ID. Admin privileges required."
    )
    @DeleteMapping("/{id}")
    ResponseEntity<GenericResponse<Void>> deletePackage(@PathVariable String id) throws PackageNotFoundException;

    @Operation(
            summary = "Delete all packages",
            description = "Deletes all packages in the system. Admin only action."
    )
    @DeleteMapping("/all")
    ResponseEntity<GenericResponse<Void>> deleteAllPackages() throws PackageNotFoundException;
}
