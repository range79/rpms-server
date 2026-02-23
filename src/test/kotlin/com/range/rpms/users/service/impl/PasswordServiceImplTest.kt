package com.range.rpms.users.service.impl

import com.range.rpms.common.enums.MailType
import com.range.rpms.common.service.EmailService
import com.range.rpms.tokens.registerToken.domain.entity.ResetToken
import com.range.rpms.tokens.registerToken.service.ResetTokenService
import com.range.rpms.users.domain.entity.AccountStatus
import com.range.rpms.users.domain.entity.User
import com.range.rpms.users.dto.ResetPasswordRequest
import com.range.rpms.users.exception.TokenNotFoundException
import com.range.rpms.users.exception.UserNotFoundException
import com.range.rpms.users.properties.PasswordResetProperties
import com.range.rpms.users.service.UserService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.crypto.password.PasswordEncoder
@ExtendWith(MockitoExtension::class)
class PasswordServiceImplTest {

    @Mock lateinit var emailService: EmailService
    @Mock lateinit var resetTokenService: ResetTokenService
    @Mock lateinit var userService: UserService
    @Mock lateinit var passwordEncoder: PasswordEncoder

    private lateinit var passwordResetProperties: PasswordResetProperties
    private lateinit var service: PasswordServiceImpl


    @BeforeEach
    fun setUp() {
        passwordResetProperties = PasswordResetProperties(
            "https://test"
        )

        service = PasswordServiceImpl(
            emailService,
            resetTokenService,
            userService,
            passwordResetProperties,
            passwordEncoder
        )
    }


    @Test
    fun `should throw when user not found`() {

        `when`(userService.findByEmail("test@mail.com"))
            .thenReturn(null)

        assertThrows<UserNotFoundException> {
            service.sendPasswordResetEmail("test@mail.com")
        }
    }


    @Test
    fun `should send reset email successfully`() {

        val user = User(
            id = null,
            username = "test",
            password = "pass",
            email = "test@mail.com",
            role = com.range.rpms.users.domain.entity.Role.USER,
            accountStatus = AccountStatus.ACTIVE
        )

        val tokenEntity =
            mock(ResetToken::class.java)

        `when`(tokenEntity.token).thenReturn("abc123")
        `when`(tokenEntity.ttl).thenReturn(15L)

        `when`(userService.findByEmail("test@mail.com"))
            .thenReturn(user)

        `when`(resetTokenService.createToken("test@mail.com"))
            .thenReturn(tokenEntity)

        service.sendPasswordResetEmail("test@mail.com")

    }




    @Test
    fun `should throw when token invalid`() {

        `when`(resetTokenService.validateToken("bad-token"))
            .thenReturn(null)

        val request = ResetPasswordRequest("bad-token", "newpass")

        assertThrows<TokenNotFoundException> {
            service.verifyPasswordResetEmail(request)
        }
    }


    @Test
    fun `should update password successfully`() {

        val user = User(
            id = null,
            username = "range",
            password = "oldpass",
            email = "test@mail.com",
            role = com.range.rpms.users.domain.entity.Role.USER,
            accountStatus =AccountStatus.ACTIVE
        )

        val request = ResetPasswordRequest("good-token", "newpass")

        `when`(resetTokenService.validateToken("good-token"))
            .thenReturn("test@mail.com")

        `when`(userService.findByEmail("test@mail.com"))
            .thenReturn(user)

        `when`(passwordEncoder.encode("newpass"))
            .thenReturn("encodedpass")

        `when`(userService.save(user))
            .thenReturn(user)



        verify(passwordEncoder, times(1)).encode("newpass")
        verify(userService, times(1)).save(user)
    }
}