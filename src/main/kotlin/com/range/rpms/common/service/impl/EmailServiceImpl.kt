package com.range.rpms.common.service.impl

import com.range.rpms.common.service.EmailService
import jakarta.mail.internet.MimeMessage
import org.slf4j.LoggerFactory
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class EmailServiceImpl(
    private val mailSender: JavaMailSender
) : EmailService {

    private val log = LoggerFactory.getLogger(EmailServiceImpl::class.java)

    @Async
    override fun sendEmail(
        to: String,
        subject: String,
        content: String,
        isHtml: Boolean
    ) {

        try {
            if (isHtml) {
                val message: MimeMessage = mailSender.createMimeMessage()
                val helper = MimeMessageHelper(
                    message,
                    true,
                    "UTF-8"
                )
                helper.setTo(to)
                helper.setSubject(subject)
                helper.setText(content, true)

                mailSender.send(message)

            } else {
                val message = SimpleMailMessage()
                message.setTo(to)
                message.subject = subject
                message.text = content

                mailSender.send(message)
            }
            log.info("Mail sent to {} (html={})", to, isHtml)
        } catch (ex: Exception) {
            log.error("Mail send failed: {}", ex.message, ex)
        }
    }
}