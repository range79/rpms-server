package com.range.rpms.dao.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "packages")
@Getter
@Setter
public class PackageEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private String author;
    private String version;
    private byte[] content;
    private Dependencies[] dependencies;
}
