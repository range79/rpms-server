package com.range.rpms.exception;

import com.range.rpms.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(value = {
          PackageNotFoundException.class
    })
    public ResponseEntity<ErrorResponseDto> handle(Exception e) {
        String message = e.getMessage();
        String details = e.getClass().getName();


        ErrorResponseDto errorResponseDto =
                new ErrorResponseDto(HttpStatus.NOT_FOUND,message,details,LocalDateTime.now());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }
}
