package com.range.rpms.controller;

import com.range.rpms.dao.model.Dependencies;
import com.range.rpms.dto.PackageDto;
import com.range.rpms.exception.PackageNotFoundException;
import com.range.rpms.service.PackageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/package")
public class PackageController {
    private final PackageService packageService;


    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }
    /*
     * This endpoint is for admins only.
     * Admins can check all files in the database to verify if any are missing.
     * If no files are found, an empty response will be returned.
     */
    @GetMapping("/all")
    public ResponseEntity<List<PackageDto>> getAllPackages() {
        List<PackageDto> packages = packageService.getAllPackages();
        if (packages.isEmpty()) {
            return ResponseEntity.noContent().build(); // Empty response if no packages are found
        }
        return ResponseEntity.ok(packages);

    }
    /*
     * This endpoint is for searching packages.
     * Users can search for packages using the following command in the Range Package Manager client:
     * rpmc search {packageName}
     */
    @GetMapping("/search/{packageName}")
    public ResponseEntity<List<PackageDto>> searchpackage(@PathVariable String packageName){
        return ResponseEntity.ok(packageService.searchPackage(packageName));
    }
    /*
     * This endpoint is for downloading packages.
     * The Range Package Manager accesses this endpoint to find the requested package
     * and initiate the download process.
     */

    @GetMapping("/download/{packageid}")
    public ResponseEntity<ByteArrayResource> DownloadPackageId(@PathVariable String packageid) throws PackageNotFoundException {

        ByteArrayResource resource = packageService.DownloadPackageById(packageid);  return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; packagename=\"" + packageid + "\"")
                .body(resource);
    }



    @DeleteMapping("/delete/{packageName}")
    public ResponseEntity<String> deletePackage(@PathVariable String packageName) throws PackageNotFoundException {
        packageService.deletePackage(packageName);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/upload")
    public ResponseEntity<PackageDto> UploadPackage( @RequestParam("file") MultipartFile file,
                                                     @RequestParam("packageName") String name,
                                                     @RequestParam("packageDescription") String description,
                                                     @RequestParam("packageVersion")String version,
                                                     @RequestParam("dependencies")Dependencies[] dependencity
    )throws PackageNotFoundException, IOException {
        return ResponseEntity.ok(packageService.addPackage(file,name,description,version,dependencity));
    }

}
