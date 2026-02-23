package com.range.rpms.common.exception

import org.springframework.http.HttpStatus

class PasswordEncoderException(
    msg: String = "An error occurred while encoding the password"
) : BaseException(msg, HttpStatus.INTERNAL_SERVER_ERROR)