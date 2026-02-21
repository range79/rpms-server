package com.range.rpms.users.exception

import com.range.rpms.common.exception.BaseException
import org.springframework.http.HttpStatus

class AccountBannedException(msg:String="Account is banned"): BaseException(msg, HttpStatus.BAD_REQUEST) {
}