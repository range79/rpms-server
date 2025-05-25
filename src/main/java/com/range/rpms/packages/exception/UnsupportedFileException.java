package com.range.rpms.packages.exception;

import com.range.rpms.common.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class UnsupportedFileException extends AbstractExceptionHandler {
    public UnsupportedFileException(String message) {
        super(message, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
