package com.range.rpms.util;
import com.range.rpms.dto.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponseUtil {
    private static final Logger logger = LoggerFactory.getLogger(ErrorResponseUtil.class);

    public static ResponseEntity<GenericResponse<Void>> build(HttpStatus status, String message) {
        if (status.is5xxServerError()) {
            logger.error("server error: {}", message);
        } else {
            logger.warn("client error: {}", message);
        }

        return ResponseEntity.status(status)
                .body(new GenericResponse<>(false, message, status.value(), null));
    }
}
