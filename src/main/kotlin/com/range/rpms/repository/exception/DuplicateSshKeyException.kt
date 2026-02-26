package com.range.rpms.repository.exception

import com.range.rpms.common.exception.BaseException
import org.springframework.http.HttpStatus

class DuplicateSshKeyException(
    message: String = "SSH key already exists for this user."
) : BaseException(message, HttpStatus.CONFLICT)