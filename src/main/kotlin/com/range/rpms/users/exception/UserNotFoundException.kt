package com.range.rpms.users.exception

import com.range.rpms.common.exception.BaseException
import org.springframework.http.HttpStatus

class UserNotFoundException (msg: String="User Not found"):
    BaseException(
    msg,
    HttpStatus.NOT_FOUND,
)