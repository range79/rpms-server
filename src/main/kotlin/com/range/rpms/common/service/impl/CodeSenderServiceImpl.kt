package com.range.rpms.common.service.impl

import com.range.rpms.common.service.CodeSenderService
import com.range.rpms.common.service.EmailService
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class CodeSenderServiceImpl(
    private val emailService: EmailService,
) : CodeSenderService {

    override fun sendResetPassword(to: String, resetPasswordLink: String) {
        TODO()

    }

    override fun send2FACode(to: String, twoFACode: String) {
        TODO("Not yet implemented")
    }
}