package com.range.rpms.exception.user;

import com.range.rpms.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends AbstractExceptionHandler {
    public UserAlreadyExistsException(String message) {super(message, HttpStatus.CONFLICT);}
}