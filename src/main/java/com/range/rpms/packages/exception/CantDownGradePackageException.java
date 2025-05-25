package com.range.rpms.packages.exception;

import com.range.rpms.common.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class CantDownGradePackageException extends AbstractExceptionHandler {
    public CantDownGradePackageException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
