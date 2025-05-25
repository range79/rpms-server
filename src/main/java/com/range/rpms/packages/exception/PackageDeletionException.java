package com.range.rpms.packages.exception;

import com.range.rpms.common.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class PackageDeletionException extends AbstractExceptionHandler {
  public PackageDeletionException(String message) {
    super(message, HttpStatus.NOT_FOUND);
  }
}
