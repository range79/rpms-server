package com.range.rpms.common.exception;

import com.range.rpms.common.dto.GenericResponse;
import com.range.rpms.common.util.ErrorResponseUtil;
import com.range.rpms.error.domain.dto.ErrorResponse;
import com.range.rpms.error.service.ErrorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Component
public class GlobalExceptionHandler
 {
private ErrorResponseUtil errorResponseUtil;
public GlobalExceptionHandler(ErrorResponseUtil errorResponseUtil)
{
    this.errorResponseUtil=errorResponseUtil;
}


    @ExceptionHandler(AbstractExceptionHandler.class)
    public ResponseEntity<ErrorResponse> handleException(AbstractExceptionHandler e) {
        return errorResponseUtil.build(e.getHttpStatus(),e);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return errorResponseUtil.build(HttpStatus.INTERNAL_SERVER_ERROR,e);
    }

}
