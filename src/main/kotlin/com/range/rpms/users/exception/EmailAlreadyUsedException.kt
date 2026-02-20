package com.range.rpms.users.exception

import com.range.rpms.common.exception.BaseException
import org.springframework.http.HttpStatus

class EmailAlreadyUsedException(email: String):
    BaseException("Email ${email} Already Used", HttpStatus.CONFLICT)