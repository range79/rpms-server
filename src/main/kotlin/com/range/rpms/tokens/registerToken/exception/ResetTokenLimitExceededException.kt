package com.range.rpms.tokens.registerToken.exception

import com.range.rpms.common.exception.BaseException
import org.springframework.http.HttpStatus

class ResetTokenLimitExceededException(
    msg: String = "Reset token request limit exceeded. Please try again later."
) : BaseException(msg, HttpStatus.TOO_MANY_REQUESTS)