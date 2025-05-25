package com.range.rpms.packages.dto;


import com.range.rpms.packages.enums.PackageVisibility;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadPackageRequest
{
    @NotBlank
    private String name;
    private String description;
    private double version;
    @NotNull
    private MultipartFile file;
    private PackageVisibility packageVisibility;
}
