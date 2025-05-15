package com.range.rpms.exception.pkg;

import com.range.rpms.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class UnsupportedFileException extends AbstractExceptionHandler {
    public UnsupportedFileException(String message) {
        super(message, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
