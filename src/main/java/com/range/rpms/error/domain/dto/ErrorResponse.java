package com.range.rpms.error.domain.dto;

import lombok.Builder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Builder
public class ErrorResponse {
    private String message;
    private String detail;
    private LocalDateTime timestamp;
}
