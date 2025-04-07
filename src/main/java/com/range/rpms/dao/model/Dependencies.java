package com.range.rpms.dao.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class Dependencies {
    @Field(name = "dep_name")
    private String name;
    @Field(name = "dep_version")
    private String version;
}
