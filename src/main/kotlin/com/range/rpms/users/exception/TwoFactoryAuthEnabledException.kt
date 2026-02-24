package com.range.rpms.users.exception

import com.range.rpms.common.exception.BaseException
import org.springframework.http.HttpStatus

class TwoFactoryAuthEnabledException(msg: String="Two factory auth enabled check your email")
    : BaseException(
        msg,
        HttpStatus.UNAUTHORIZED
    )