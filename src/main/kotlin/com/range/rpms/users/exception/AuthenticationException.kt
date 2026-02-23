package com.range.rpms.users.exception

import com.range.rpms.common.exception.BaseException
import org.springframework.http.HttpStatus

class AuthenticationException(
    msg: String = "Username or password is incorrect"
) : BaseException(msg, HttpStatus.UNAUTHORIZED)