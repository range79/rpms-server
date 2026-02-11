package com.range.rpms.users.exception;

import com.range.rpms.common.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends AbstractExceptionHandler {
    public UserAlreadyExistsException(String message) {super(message, HttpStatus.CONFLICT);}
}