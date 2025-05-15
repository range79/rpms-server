package com.range.rpms.exception.pkg;

import com.range.rpms.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class PackageCantUploadException extends AbstractExceptionHandler {
    public PackageCantUploadException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
