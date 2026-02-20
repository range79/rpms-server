package com.range.rpms.common.exception

import org.springframework.http.HttpStatus

abstract class BaseException(msg: String, val status: HttpStatus): RuntimeException(msg) {
}