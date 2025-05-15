package com.range.rpms.exception.pkg;

import com.range.rpms.exception.AbstractExceptionHandler;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class CantDownGradePackageException extends AbstractExceptionHandler {
    public CantDownGradePackageException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
