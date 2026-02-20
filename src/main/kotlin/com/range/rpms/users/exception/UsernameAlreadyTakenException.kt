package com.range.rpms.users.exception

import com.range.rpms.common.exception.BaseException
import org.springframework.http.HttpStatus

class UsernameAlreadyTakenException(username: String): BaseException("User name ${username} already taken", HttpStatus.CONFLICT)