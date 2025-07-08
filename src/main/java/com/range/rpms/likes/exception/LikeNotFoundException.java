package com.range.rpms.likes.exception;

import com.range.rpms.common.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class LikeNotFoundException extends AbstractExceptionHandler {
  public LikeNotFoundException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }
}
