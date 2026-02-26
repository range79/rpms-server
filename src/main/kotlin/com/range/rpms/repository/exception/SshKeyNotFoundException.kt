package com.range.rpms.repository.exception

import com.range.rpms.common.exception.BaseException
import org.springframework.http.HttpStatus

class SshKeyNotFoundException(
    message: String = "SSH key not found."
) : BaseException(message, HttpStatus.NOT_FOUND)