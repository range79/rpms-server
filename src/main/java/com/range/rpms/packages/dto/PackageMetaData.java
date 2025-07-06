package com.range.rpms.packages.dto;

import com.range.rpms.packages.enums.PackageVisibility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PackageMetaData {

    private String id;
    private String name;
    private String description;
    private Long author;
    private Double version;
    private PackageVisibility packageVisibility;
}
