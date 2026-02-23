package com.range.rpms.tokens.refreshtoken.exception

import com.range.rpms.common.exception.BaseException
import org.springframework.http.HttpStatus

class InvalidRefreshTokenException(
    msg: String = "Invalid or expired refresh token"
) : BaseException(
    msg,
    HttpStatus.UNAUTHORIZED
)