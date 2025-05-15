package com.range.rpms.exception.user;

import com.range.rpms.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AbstractExceptionHandler {
    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
