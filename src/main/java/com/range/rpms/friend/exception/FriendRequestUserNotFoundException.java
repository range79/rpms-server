package com.range.rpms.friend.exception;

import com.range.rpms.common.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class FriendRequestUserNotFoundException extends AbstractExceptionHandler {
    public FriendRequestUserNotFoundException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
