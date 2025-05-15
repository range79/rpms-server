package com.range.rpms.exception;

import org.springframework.http.HttpStatus;

public class AbstractExceptionHandler extends RuntimeException {
    private HttpStatus httpStatus;
    public AbstractExceptionHandler(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
