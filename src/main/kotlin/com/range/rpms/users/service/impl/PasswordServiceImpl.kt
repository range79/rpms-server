package com.range.rpms.users.service.impl

import com.range.rpms.common.enums.MailType
import com.range.rpms.common.exception.PasswordEncoderException
import com.range.rpms.common.service.EmailService
import com.range.rpms.tokens.registerToken.service.ResetTokenService
import com.range.rpms.users.domain.entity.AccountStatus
import com.range.rpms.users.domain.entity.User
import com.range.rpms.users.dto.ResetPasswordRequest
import com.range.rpms.users.exception.TokenNotFoundException
import com.range.rpms.users.exception.UserNotFoundException
import com.range.rpms.users.properties.PasswordResetProperties
import com.range.rpms.users.service.PasswordService
import com.range.rpms.users.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder

@Service
class PasswordServiceImpl(
    private val emailService: EmailService,
    private val resetTokenService: ResetTokenService,
    private val userService: UserService,
    private val passwordResetProperties: PasswordResetProperties,
    private val passwordEncoder: PasswordEncoder,

    ) : PasswordService {

    override fun sendPasswordResetEmail(email: String) {


        val user = userService.findByEmail(email) ?: throw UserNotFoundException()


        val tokenEntity = resetTokenService.createToken(email)
        val token = tokenEntity.token

        val resetLink = UriComponentsBuilder
            .fromUriString(passwordResetProperties.frontendUrl)
            .path("/reset-password")
            .queryParam("token", token)
            .build()
            .toUriString()


        val variables: Map<String, Any> = mapOf(
            "username" to user.username,
            "resetLink" to resetLink,
            "expireMinutes" to tokenEntity.ttl,
        )
        emailService.sendTemplateEmail(
            to = email,
            type = MailType.RESET_PASSWORD,
            variables = variables,
            fallbackText = null
        )
    }

    override fun verifyPasswordResetEmail(resetPassword: ResetPasswordRequest) {

        val email = resetTokenService.validateToken(resetPassword.token)
            ?: throw TokenNotFoundException()
        val user = userService.findByEmail(email) ?: throw UserNotFoundException()



        user.password = passwordEncoder.encode(resetPassword.newPassword) ?: throw PasswordEncoderException()

        userService.save(user)
    }


}