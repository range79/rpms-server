package com.range.rpms.controller.pkg;

import com.range.rpms.dto.ApiResponse;
import com.range.rpms.dto.pkg.PackageMetaData;
import com.range.rpms.dto.pkg.UploadPackageRequest;
import com.range.rpms.exception.pkg.PackageNotFoundException;
import com.range.rpms.service.PackageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/packages")
public class PackageController {
    private final PackageService packageService;


    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }


    /**
     * This endpoint is for downloading packages.
     * The Range Package Manager accesses this endpoint to find the requested package
     * and initiate the download process.
     */

    @Operation(
            summary = "Download a package by ID",
            description = "Downloads a binary package file using its ID."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Package found and downloaded",
                    content = @Content(mediaType = "application/octet-stream")
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Package not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(value = """
                {
                    "success": false,
                    "message": "Package with ID not found",
                    "statusCode": 404,
                    "data": null
                }
            """))
            )
    })

    @GetMapping("/{packageId}")
    public ResponseEntity<ByteArrayResource> downloadPackage(@PathVariable String packageId) throws PackageNotFoundException {

        ByteArrayResource resource = packageService.downloadPackageById(packageId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + packageId + "\"")
                .body(resource);
    }

    /**
     * This endpoint for only
     * admin no one can access it
     * this endpoint delete package by package name
     */
    @Operation(summary = "Delete a package by name",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Successfully response",
                            content = @Content(
                                    mediaType = "application/json",

                                    schema = @Schema(implementation = ApiResponse.class),
                                    examples = @ExampleObject(value = """
                                            {
                                            "success": true,
                                            "message": "package deleted successfully",
                                            "statusCode": 200,
                                            "data": null
                                            }
                                            """)

                            )),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "failure",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class),
                                    examples = @ExampleObject(value = """
                                            {
                                            "success": false,
                                            "message": "Package {packageName} not found",
                                            "statusCode": 404,
                                            "data": null
                                            }
                                            """)
                            )
                    )

            }
    )
    @DeleteMapping("/delete/{packageName}")
    public ResponseEntity<ApiResponse<Void>> deletePackage(@PathVariable String packageName) throws PackageNotFoundException {

        packageService.deletePackage(packageName);

        return ResponseEntity.ok(new
                        ApiResponse<>(true,
                        "packages deleted successful",
                        HttpStatus.OK.value(),
                        null
                )
        );
    }

    @Operation(summary = "Delete a package by name",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Successfully response",
                            content = @Content(
                                    mediaType = "application/json",

                                    schema = @Schema(implementation = ApiResponse.class),
                                    examples = @ExampleObject(value = """
                                            {
                                            "success": true,
                                            "message": "all packages deleted successfully",
                                            "statusCode": 200,
                                            "data": null
                                            }
                                            """)

                            )),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "failure",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponse.class),
                                    examples = @ExampleObject(value = """
                                            {
                                            "success": false,
                                            "message": "Package not found",
                                            "statusCode": 404,
                                            "data": null
                                            }
                                            """)
                            )
                    )
            }
    )
    @DeleteMapping("/delete/all")
    public ResponseEntity<ApiResponse<Void>> deleteall()
    {
        packageService.deleteAllPackages();
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "All packages deleted successfully",
                HttpStatus.OK.value(),
                null
        ));

    }
    @Operation(
            summary = "Upload a package",
            description = "Uploads a package using multipart form data. The package must include valid metadata and a supported file format."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Package uploaded successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(value = """
                {
                  "success": true,
                  "message": "File uploaded successfully",
                  "statusCode": 201,
                  "data": {
                    "id": "pkg-123456",
                    "name": "package.zip",
                    "description": "A sample package",
                    "author": "range",
                    "version": 1.0
                  }
                }
            """)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid upload request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(value = """
                {
                  "success": false,
                  "message": "Unsupported file type. Allowed: zip, tar, gz...",
                  "statusCode": 400,
                  "data": null
                }
            """)
                    )
            )
    })
    @PostMapping("/upload")
    public ResponseEntity<ApiResponse <PackageMetaData>> uploadPackage(@ModelAttribute UploadPackageRequest uploadPackageRequest)
            throws PackageNotFoundException, IOException {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        true,
                        "File uploaded successfully",
                        HttpStatus.CREATED.value(),
                        packageService.addPackage(uploadPackageRequest)
                )
        );


    }
    @Operation(
            summary = "Update an existing package by ID",
            description = "Updates an existing package with new metadata and file. Uses multipart/form-data."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Package updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(value = """
                {
                  "success": true,
                  "message": "File updated successfully",
                  "statusCode": 200,
                  "data": {
                    "id": "pkg-123456",
                    "name": "package-updated",
                    "description": "Updated description",
                    "author": "range",
                    "version": 2
                  }
                }
            """)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Package not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(value = """
                {
                  "success": false,
                  "message": "Package with ID {id} not found",
                  "statusCode": 404,
                  "data": null
                }
            """)
                    )
            )
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<PackageMetaData>> updatePackage(@PathVariable String id,@ModelAttribute UploadPackageRequest uploadPackageRequest) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                true,
                "File updated successfully",
                HttpStatus.OK.value(),
                packageService.updatePackage(id, uploadPackageRequest)
        ));
    }

}
