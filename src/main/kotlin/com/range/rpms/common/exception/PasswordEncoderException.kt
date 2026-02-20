package com.range.rpms.common.exception

import org.springframework.http.HttpStatus

class PasswordEncoderException(msg: String): BaseException(msg, HttpStatus.INTERNAL_SERVER_ERROR) {
}