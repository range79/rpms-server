package com.range.rpms.exception.pkg;

import com.range.rpms.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class FileReadException extends AbstractExceptionHandler {
    public FileReadException(String message) {
        super(message, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
