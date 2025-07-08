package com.range.rpms.likes.exception;

import com.range.rpms.common.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class PackageAlreadyLikedException extends AbstractExceptionHandler {
  public PackageAlreadyLikedException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }
}
