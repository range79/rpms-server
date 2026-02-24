package com.range.rpms.tokens.twofactor.service

import com.range.rpms.tokens.twofactor.domain.entity.TwoFactorAuthToken
import com.range.rpms.tokens.twofactor.dto.TwoFactorPending


interface TwoFactorService {

    fun createPendingLogin(
        userId: String,
        email: String
    ): TwoFactorPending



    fun verify(
        pendingId: String,
        code: String
    ): String



    fun cancel(pendingId: String)
}