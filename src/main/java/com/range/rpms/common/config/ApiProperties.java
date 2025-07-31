package com.range.rpms.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "app.base-path")
public class ApiProperties {
    private String basePath="/v2/app";
    //todo fix this class for auto define app base default path

}

