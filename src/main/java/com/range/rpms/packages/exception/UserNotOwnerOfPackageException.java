package com.range.rpms.packages.exception;

import com.range.rpms.common.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class UserNotOwnerOfPackageException extends AbstractExceptionHandler {
    public UserNotOwnerOfPackageException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
