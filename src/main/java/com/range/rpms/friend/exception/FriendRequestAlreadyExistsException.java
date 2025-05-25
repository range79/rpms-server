package com.range.rpms.friend.exception;

import com.range.rpms.common.exception.AbstractExceptionHandler;
import org.springframework.http.HttpStatus;

public class FriendRequestAlreadyExistsException extends AbstractExceptionHandler {
    public FriendRequestAlreadyExistsException(String message) {super(message, HttpStatus.CONFLICT);}}