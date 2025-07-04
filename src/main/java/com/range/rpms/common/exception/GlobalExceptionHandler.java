package com.range.rpms.common.exception;

import com.range.rpms.common.dto.GenericResponse;
import com.range.rpms.common.util.ErrorResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AbstractExceptionHandler.class)
    public ResponseEntity<GenericResponse<Void>> handleException(AbstractExceptionHandler e) {

        return ErrorResponseUtil
                .build(e.getHttpStatus()
                        ,e.getMessage()
                );

    }

}
