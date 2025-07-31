package com.range.rpms.common.exception;
import com.range.rpms.error.dto.ErrorResponse;
import com.range.rpms.error.domain.model.ErrorTypes;
import com.range.rpms.error.domain.model.Errors;
import com.range.rpms.error.service.ErrorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class ErrorResponseUtil {
    private ErrorService errorService;
    public ErrorResponseUtil(ErrorService errorService) {
        this.errorService = errorService;
    }
    private static final Logger logger = LoggerFactory.getLogger(ErrorResponseUtil.class);

    public ResponseEntity<ErrorResponse> build(HttpStatus status, Exception e) {

        ErrorTypes errorTypes;
        if (status.is5xxServerError()) {
            logger.error("server error: {}", e.getMessage());
            errorTypes = ErrorTypes.SERVER_ERROR;
        } else {
            logger.warn("client error: {}", e.getMessage());
            errorTypes = ErrorTypes.CLIENT_ERROR;
        }
        Errors errors = Errors
                .builder()
                .detail(Arrays.toString(e.getStackTrace()))
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .errorType(errorTypes)
                .build();
        ErrorResponse errorResponse=  errorService.saveError(errors);
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }
}
