package com.range.rpms.common.service

import com.range.rpms.common.enums.MailType

interface EmailService {

    fun sendTemplateEmail(
        to: String,
        type: MailType,
        variables: Map<String, Any>,
        fallbackText: String? = null
    )

    fun sendPlainEmail(
        to: String,
        subject: String,
        content: String
    )
}