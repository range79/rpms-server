package com.range.rpms.tokens.registerToken.service

import com.range.rpms.tokens.registerToken.domain.entity.ResetToken

interface ResetTokenService {
    fun createToken(email:String): ResetToken
    fun validateToken(token:String):Boolean
}