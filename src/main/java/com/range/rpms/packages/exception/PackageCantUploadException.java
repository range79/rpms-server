package com.range.rpms.packages.exception;

import com.range.rpms.common.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class PackageCantUploadException extends AbstractExceptionHandler {
    public PackageCantUploadException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
