package com.range.rpms.exception.pkg;

import com.range.rpms.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class PackageNotFoundException extends AbstractExceptionHandler {
  public PackageNotFoundException(String message) {
    super(message, HttpStatus.NO_CONTENT);
  }
}
