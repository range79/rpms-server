package com.range.rpms.packages.controller.user;

import com.range.rpms.common.dto.GenericResponse;
import com.range.rpms.packages.api.user.MyPackagesApi;
import com.range.rpms.packages.dto.PackageMetaData;
import com.range.rpms.packages.dto.UploadPackageRequest;
import com.range.rpms.packages.mapper.PackageVisibilityMapper;
import com.range.rpms.packages.service.MyPackageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MyPackagesController implements MyPackagesApi {
    private final MyPackageService myPackageService;


    public MyPackagesController(MyPackageService myPackageService) {
        this.myPackageService = myPackageService;

    }

    public ResponseEntity<GenericResponse<List<PackageMetaData>>> getAllPackages() {
        return ResponseEntity.ok(new GenericResponse<>(true,
                        "List of packages you've published",
                        HttpStatus.OK.value(),
                        myPackageService.getAllPackages()
                )
        );
    }

    @Override
    public ResponseEntity<GenericResponse<Void>> deleteAllPackages() {

        myPackageService.deleteMyAllPackages();

        return ResponseEntity.ok(new GenericResponse<>(
                true,
                "Successfully deleted all your packages",
                HttpStatus.OK.value(),
                null
        ));
    }

    @Override
    public ResponseEntity<GenericResponse<PackageMetaData>> updatePackage(String id, UploadPackageRequest uploadPackageRequest) throws IOException {
        return ResponseEntity.ok(new GenericResponse<>(
                true ,
                "Package "+id+" updated",
                HttpStatus.OK.value(),
                myPackageService.updatePackage(id,uploadPackageRequest)
        ));
    }

    @Override
    public ResponseEntity<GenericResponse<Void>> deletePackageById(String id) {
        myPackageService.deletePackageById(id);
        return ResponseEntity.ok(new GenericResponse<>(
                true,
                "Package id:"+ id +"deleted successfully",
                HttpStatus.OK.value(),
                null
        ));
    }

    @Override
    public ResponseEntity<GenericResponse<Void>> setPackageVisibility(String packageId, int visibilityCode) {
        myPackageService.setPackageVisibility(packageId,visibilityCode);
        return ResponseEntity.ok(new GenericResponse<>(
                true,
                "Your package is now"+ PackageVisibilityMapper.getPackageVisibility(visibilityCode),
                HttpStatus.OK.value(),
                null
        ));
    }


}
