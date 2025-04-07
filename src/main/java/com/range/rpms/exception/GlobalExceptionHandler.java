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
            PackageCantUploadException.class
    })
    public ResponseEntity<ErrorResponseDto> handleException(Exception e) {
        String message = e.getMessage();


        ErrorResponseDto errorResponseDto =
                new ErrorResponseDto(HttpStatus.BAD_REQUEST,message,LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @ExceptionHandler(value = {
          PackageNotFoundException.class
    })
    public ResponseEntity<ErrorResponseDto> handle(Exception e) {

        //store message and details to a variable
        String message = e.getMessage();


        ErrorResponseDto errorResponseDto =
                new ErrorResponseDto(HttpStatus.NOT_FOUND,message,LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }


}
