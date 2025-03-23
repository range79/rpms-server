package com.range.rpms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDto {
    private HttpStatus code;
    private String message;
    private LocalDateTime timestamp;

}
