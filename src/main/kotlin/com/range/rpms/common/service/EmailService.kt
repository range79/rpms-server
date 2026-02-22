package com.range.rpms.common.service

interface EmailService {

    fun sendEmail(
        to: String,
        subject: String,
        content: String,
        isHtml: Boolean
    )
}