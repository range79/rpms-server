package com.range.rpms.users.exception

import com.range.rpms.common.exception.BaseException
import org.springframework.http.HttpStatus


class TokenNotFoundException(msg: String="Token not found"):
    BaseException(msg, HttpStatus.BAD_REQUEST)