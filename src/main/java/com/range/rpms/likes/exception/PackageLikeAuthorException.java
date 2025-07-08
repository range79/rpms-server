package com.range.rpms.likes.exception;

import com.range.rpms.common.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class PackageLikeAuthorException extends AbstractExceptionHandler {
    public PackageLikeAuthorException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
