package com.range.rpms.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "GenericResponse", description = "Standard response wrapper")
public class GenericResponse<T> {
    @Schema(description = "Success flag")
    private boolean success;

    @Schema(description = "Optional message")
    private String message;

    @Schema(description = "HTTP status code")
    private int statusCode;

    @Schema(description = "Returned data", implementation = Object.class)
    private T data;

}
