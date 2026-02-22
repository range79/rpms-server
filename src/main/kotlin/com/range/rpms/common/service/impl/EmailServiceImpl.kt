package com.range.rpms.common.service.impl

import com.range.rpms.common.enums.MailType
import com.range.rpms.common.properties.MailProperties
import com.range.rpms.common.service.EmailService
import jakarta.mail.internet.MimeMessage
import jakarta.mail.internet.InternetAddress
import org.slf4j.LoggerFactory
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context
import org.thymeleaf.spring6.SpringTemplateEngine

@Service
class EmailServiceImpl(
    private val mailSender: JavaMailSender,
    private val templateEngine: SpringTemplateEngine,
    private val mailProperties: MailProperties
) : EmailService {

    private val log = LoggerFactory.getLogger(javaClass)

    @Async
    override fun sendTemplateEmail(
        to: String,
        type: MailType,
        variables: Map<String, Any>,
        fallbackText: String?
    ) {
        try {
            val context = Context().apply {
                variables.forEach { (k, v) -> setVariable(k, v) }
            }

            val html = templateEngine.process(
                "mail/${type.template}",
                context
            )

            val message: MimeMessage = mailSender.createMimeMessage()
            val helper = MimeMessageHelper(message, true, "UTF-8")


            helper.setFrom(
                InternetAddress(
                    mailProperties.from,
                    mailProperties.name
                )
            )

            helper.setTo(to)
            helper.setSubject(type.subject)

            if (!fallbackText.isNullOrBlank()) {
                helper.setText(fallbackText, html)
            } else {
                helper.setText(html, true)
            }

            mailSender.send(message)

            log.info("Template mail sent to={} type={}", to, type)

        } catch (ex: Exception) {
            log.error("Template mail failed type={}", type, ex)
        }
    }

    @Async
    override fun sendPlainEmail(
        to: String,
        subject: String,
        content: String
    ) {
        try {
            val message = SimpleMailMessage()

            message.from = mailProperties.from

            message.setTo(to)
            message.subject = subject
            message.text = content

            mailSender.send(message)

            log.info("Plain mail sent to {}", to)

        } catch (ex: Exception) {
            log.error("Plain mail failed", ex)
        }
    }
}