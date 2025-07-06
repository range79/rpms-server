package com.range.rpms.packages.domain.model;

import com.range.rpms.packages.enums.PackageVisibility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "packages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PackageEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private Long author;
    private double version;
    private byte[] content;
    private PackageVisibility packageVisibility;
}
