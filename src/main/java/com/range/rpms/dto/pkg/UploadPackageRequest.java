package com.range.rpms.dto.pkg;


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
    @NotNull
    private String author;
    private double version;
    @NotNull
    private MultipartFile file;
}
