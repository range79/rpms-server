package com.range.rpms.dao.model;

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
    private String author;
    private double version;
    private byte[] content;
}
