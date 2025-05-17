package com.range.rpms.packages.api.user;

import com.range.rpms.common.dto.GenericResponse;
import com.range.rpms.packages.dto.PackageMetaData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/my-packages")
public interface MyPackagesApi  {
    @GetMapping("/all")
    ResponseEntity<GenericResponse<List<PackageMetaData>>> getAllPackages();

    @DeleteMapping("/all")
    ResponseEntity<GenericResponse<Void>> deleteAllPackages();



}
