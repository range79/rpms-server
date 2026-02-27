package com.range.rpms.common.exception

import org.springframework.http.HttpStatus

class BadRequestException(
    message: String = "Bad request."
) : BaseException(
    message,
    HttpStatus.BAD_REQUEST
)