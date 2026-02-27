package com.range.rpms.repository.exception


import com.range.rpms.common.exception.BaseException
import org.springframework.http.HttpStatus

class InvalidSshPublicKeyException(
    message: String = "Invalid SSH public key."
) : BaseException(message, HttpStatus.BAD_REQUEST)