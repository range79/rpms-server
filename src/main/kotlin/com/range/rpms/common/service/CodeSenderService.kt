package com.range.rpms.common.service

interface CodeSenderService {
    fun sendResetPassword(to: String,resetPasswordLink: String)
    fun send2FACode(to: String,twoFACode: String)
}