package com.range.rpms.packages.api.user;

import com.range.rpms.common.dto.GenericResponse;
import com.range.rpms.packages.dto.PackageMetaData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Package MetaData API", description = "Provides metadata queries for packages")
@RequestMapping("/packages/user/info")
public interface PackageMetaDataApi {

    @Operation(
            summary = "Search packages by name",
            description = "Returns all packages that match the given name"
    )
    @GetMapping("/{packageName}")
    ResponseEntity<GenericResponse<List<PackageMetaData>>> searchPackage(@PathVariable String packageName);

    @Operation(
            summary = "Get all packages",
            description = "Returns all available public packages"
    )
    @GetMapping("/all")
    ResponseEntity<GenericResponse<List<PackageMetaData>>> getAllPackages();
}
