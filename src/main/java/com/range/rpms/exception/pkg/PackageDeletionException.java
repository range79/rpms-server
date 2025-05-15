package com.range.rpms.exception.pkg;

import com.range.rpms.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class PackageDeletionException extends AbstractExceptionHandler {
  public PackageDeletionException(String message) {
    super(message, HttpStatus.NOT_FOUND);
  }
}
