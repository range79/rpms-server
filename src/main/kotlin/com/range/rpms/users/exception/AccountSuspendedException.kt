package com.range.rpms.users.exception

import com.range.rpms.common.exception.BaseException
import org.springframework.http.HttpStatus

class AccountSuspendedException(msg: String="Your account is suspended. Please contact support.") :
    BaseException(msg,
    HttpStatus.UNAUTHORIZED
)