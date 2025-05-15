package com.range.rpms.dto.pkg;

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
    private String author;
    private Double version;

}
