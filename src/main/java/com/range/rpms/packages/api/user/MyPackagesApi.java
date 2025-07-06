package com.range.rpms.packages.api.user;

import com.range.rpms.common.dto.GenericResponse;
import com.range.rpms.packages.dto.PackageMetaData;
import com.range.rpms.packages.dto.UploadPackageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/v1/packages/my-packages")
@Tag(name = "MyPackagesController", description = "Operations related to authenticated user's own packages")
public interface MyPackagesApi {


    @Operation(
            summary = "Delete all your packages",
            description = "Deletes every package owned by the authenticated user"
    )
    @DeleteMapping("/all")
    ResponseEntity<GenericResponse<Void>> deleteAllPackages();

    @Operation(
            summary = "Update a package",
            description = "Updates an existing package with new metadata and file. Uses JSON body for upload request."
    )
    @PatchMapping("/{id}")
    ResponseEntity<GenericResponse<PackageMetaData>> updatePackage(
            @PathVariable String id,
            @RequestBody UploadPackageRequest uploadPackageRequest
    ) throws IOException;

    @Operation(
            summary = "Delete a package by ID",
            description = "Deletes a single package owned by the user using its package ID"
    )
    @DeleteMapping("/{id}")
    ResponseEntity<GenericResponse<Void>> deletePackageById(@PathVariable String id);

    @Operation(
            summary = "Set package visibility",
            description = """
                    Updates the visibility of a specific package.
                    Visibility codes:
                    - 0: PUBLIC
                    - 1: PRIVATE
                    - 2: ONLY_FRIEND
                    """
    )
    @PatchMapping("/{packageId}/setVisibility/{visibilityCode}")
    ResponseEntity<GenericResponse<Void>> setPackageVisibility(
            @PathVariable String packageId,
            @PathVariable int visibilityCode
    );
}
