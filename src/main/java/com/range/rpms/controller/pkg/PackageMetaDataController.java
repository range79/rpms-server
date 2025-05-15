package com.range.rpms.controller.pkg;

import com.range.rpms.dto.GenericResponse;
import com.range.rpms.dto.pkg.PackageMetaData;
import com.range.rpms.service.PackageMetaDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "info")
public class PackageMetaDataController {
    private final PackageMetaDataService packageMetaDataService;
    public PackageMetaDataController(PackageMetaDataService packageMetaDataService) {
        this.packageMetaDataService = packageMetaDataService;
    }
    /**
     * This endpoint is for admins only.
     * Admins can check all files in the database to verify if any are missing.
     * If no files are found, an empty response will be returned.
     */
    @Operation(
            summary = "Get all packages",
            description = "Returns a list of all available packages in the system."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "All packages retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponse.class),
                            examples = @ExampleObject(value = """
                {
                  "success": true,
                  "message": "all packages fetched",
                  "statusCode": 200,
                  "data": [
                    {
                      "id": "pkg-1",
                      "name": "range-core",
                      "description": "Core package of Range",
                      "author": "range79",
                      "version": 1.0
                    },
                    {
                      "id": "pkg-2",
                      "name": "range-utils",
                      "description": "Utility package",
                      "author": "range79",
                      "version": 1.2
                    }
                  ]
                }
            """)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "204",
                    description = "No packages available",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponse.class),
                            examples = @ExampleObject(value = """
                {
                  "success": false,
                  "message": "No packages available",
                  "statusCode": 204,
                  "data": null
                }
            """)
                    )
            )
    })
    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<PackageMetaData>>> getAllPackages() {
        return ResponseEntity.ok(new GenericResponse<>(true,
                "all packages fetched",
                200,
                packageMetaDataService.getAllPackages()
        ));

    }
    /**
     * This endpoint is for searching packages.
     * Users can search for packages using the following command in the Range Package Manager client:
     * rpmc search {packageName}
     */
    @Operation(
            summary = "Search packages by name",
            description = "Searches for packages matching the given name. Returns a list of matched packages."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Matching packages found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponse.class),
                            examples = @ExampleObject(value = """
                {
                  "success": true,
                  "message": "matched packages",
                  "statusCode": 200,
                  "data": [
                    {
                      "id": "pkg-1",
                      "name": "range-core",
                      "description": "Core package of Range",
                      "author": "range79",
                      "version": 1.0
                    },
                    {
                      "id": "pkg-2",
                      "name": "range-utils",
                      "description": "Utility package",
                      "author": "range79",
                      "version": 1.2
                    }
                  ]
                }
            """)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "No matching packages found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponse.class),
                            examples = @ExampleObject(value = """
                {
                  "success": false,
                  "message": "No package found with name: {packageName}",
                  "statusCode": 404,
                  "data": null
                }
            """)
                    )
            )
    })
    @GetMapping("/{packageName}")
    public ResponseEntity<GenericResponse<List<PackageMetaData>>> searchPackage(@PathVariable String packageName){

        return ResponseEntity.ok(new
                GenericResponse<>(true,
                "matched packages",
                200,
                packageMetaDataService.searchPackage(packageName)));

    }
}
