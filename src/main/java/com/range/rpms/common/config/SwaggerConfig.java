package com.range.rpms.common.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info().title("Range Package Manager Server")
                        .description("A Server for managing packages")
                        .version("v2.0.0")
                        .license(new License().name("GPL 3.0").url("https://github.com/range79/rpms-server/blob/main/LICENSE")))
                .externalDocs(new ExternalDocumentation()
                        .description("Github")
                        .url("https://github.com/range79/rpms-server"));
    }
}
