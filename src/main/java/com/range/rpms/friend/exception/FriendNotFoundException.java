package com.range.rpms.friend.exception;

import com.range.rpms.common.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class FriendNotFoundException extends AbstractExceptionHandler {
  public FriendNotFoundException(String message) {
    super(message, HttpStatus.NOT_FOUND);
  }
}
