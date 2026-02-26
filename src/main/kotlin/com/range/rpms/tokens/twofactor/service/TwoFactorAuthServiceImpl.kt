package com.range.rpms.tokens.twofactor.service

import com.range.rpms.tokens.twofactor.dto.TwoFactorPending
import org.springframework.stereotype.Service

@Service
class TwoFactorAuthServiceImpl : TwoFactorService {
    override fun createPendingLogin(
        userId: String,
        email: String
    ): TwoFactorPending {
        TODO("Not yet implemented")
    }

    override fun verify(pendingId: String, code: String): String {
        TODO("Not yet implemented")
    }

    override fun cancel(pendingId: String) {
        TODO("Not yet implemented")
    }
}