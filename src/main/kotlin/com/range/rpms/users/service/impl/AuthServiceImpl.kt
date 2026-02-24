package com.range.rpms.users.service.impl

import com.range.rpms.tokens.tokenfactory.TokenFactory
import com.range.rpms.tokens.twofactor.service.TwoFactorService
import com.range.rpms.users.dto.AuthResponse
import com.range.rpms.users.dto.LoginRequest
import com.range.rpms.users.dto.RegisterRequest
import com.range.rpms.users.dto.ResetPasswordRequest
import com.range.rpms.users.exception.TwoFactoryAuthEnabledException
import com.range.rpms.users.service.AuthService
import com.range.rpms.users.service.LoginService
import com.range.rpms.users.service.PasswordService
import com.range.rpms.users.service.RegisterService
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    val tokenFactory: TokenFactory,
    val loginService: LoginService,
    val registerService: RegisterService,
    val passwordService: PasswordService,
    val twoFactorService: TwoFactorService

)

:AuthService {
    override fun login(loginRequest: LoginRequest):AuthResponse {
        val user =loginService.login(loginRequest)
        if (!user.twoFactorEnabled) {
            return tokenFactory.generateTokens(user)
        }

        val pending = twoFactorService.createPendingLogin(user.username,user.email)

        return AuthResponse.twoFactorRequired(
            pending.id,
            pending.expiresIn
        )
    }
    override fun register(registerRequest: RegisterRequest) : AuthResponse{
        val user =registerService.register(registerRequest)
        return tokenFactory.generateTokens(user)
    }

    override fun resetPassword(resetPasswordRequest: ResetPasswordRequest) {
        passwordService.verifyPasswordResetEmail(resetPasswordRequest)
    }

    override fun forgotPassword(email: String) {
      passwordService.sendPasswordResetEmail(email)
    }

    override fun acceptTwoFactoryAuthRequest(
        pendingId: String,
        token: String
    ): AuthResponse {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }
}