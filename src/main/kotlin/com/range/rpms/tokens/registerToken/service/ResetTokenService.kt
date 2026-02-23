package com.range.rpms.tokens.registerToken.service

import com.range.rpms.tokens.registerToken.domain.entity.ResetToken

interface ResetTokenService {
    fun createToken(email:String): ResetToken

    /**
     * @return email
     */
    fun validateToken(token:String): String?
}