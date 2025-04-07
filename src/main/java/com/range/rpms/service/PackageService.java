package com.range.rpms.service;

import com.range.rpms.dao.model.Dependencies;
import com.range.rpms.dto.PackageDto;
import com.range.rpms.exception.PackageNotFoundException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PackageService {


        // Fetches all packages from the repository
        List<PackageDto> getAllPackages();

        // Searches for a package by its name
        List<PackageDto> searchPackage(String packageName) throws PackageNotFoundException;

        // Adds a new package to the repository
        PackageDto addPackage(MultipartFile file, String name, String description, String version, Dependencies[] dependencies) throws PackageNotFoundException, IOException;

        // Downloads the package content by its name
        ByteArrayResource DownloadPackageByName(String packageName) throws PackageNotFoundException;

        //Download the package content by its id
        ByteArrayResource DownloadPackageById(String packageId) throws PackageNotFoundException;

        // Deletes a package by its name
        void deletePackage(String packageName) throws PackageNotFoundException;
    }

