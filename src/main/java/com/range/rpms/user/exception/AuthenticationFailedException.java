package com.range.rpms.user.exception;

import com.range.rpms.common.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class AuthenticationFailedException extends AbstractExceptionHandler {
    public AuthenticationFailedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
