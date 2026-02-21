package com.range.rpms.registerToken.service

import com.range.rpms.registerToken.domain.entity.ResetToken

interface ResetTokenService {
    fun createToken(email:String): ResetToken
    fun validateToken(token:String):Boolean
}