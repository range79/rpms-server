package com.example.rpms.users.exception

import com.example.rpms.common.exception.BaseException
import org.springframework.http.HttpStatus

class UserNotFoundException(msg:String): BaseException(msg, HttpStatus.NOT_FOUND) {
}