package com.range.rpms.common.exception

import org.springframework.http.HttpStatus

class ForbiddenException(
    message: String = "Forbidden."
) : BaseException(message, HttpStatus.FORBIDDEN)