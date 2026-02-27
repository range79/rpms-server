package com.range.rpms.repository.controller

import com.range.rpms.common.exception.BadRequestException
import com.range.rpms.repository.api.SshKeyApi
import com.range.rpms.repository.dto.AddSshKeyRequest
import com.range.rpms.repository.dto.SshKeyResponse
import com.range.rpms.repository.service.SshKeyService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class SshKeyController(
    private val sshKeyService: SshKeyService
) : SshKeyApi {

    @ResponseStatus(HttpStatus.CREATED)
    override fun add(
        @AuthenticationPrincipal jwt: Jwt,
        @Valid @RequestBody req: AddSshKeyRequest
    ): SshKeyResponse {
        val userId = jwtUserId(jwt)
        return sshKeyService.addKey(userId, req)
    }

    override fun list(
        @AuthenticationPrincipal jwt: Jwt,
        pageable: Pageable
    ): Page<SshKeyResponse> {
        val userId = jwtUserId(jwt)
        return sshKeyService.listActiveKeys(userId, pageable)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun revoke(
        @AuthenticationPrincipal jwt: Jwt,
        id: UUID
    ) {
        val userId = jwtUserId(jwt)
        sshKeyService.revoke(id, userId)
    }

    private fun jwtUserId(jwt: Jwt): UUID {
        val sub = jwt.subject ?: throw BadRequestException("JWT subject (sub) is missing.")
        return try {
            UUID.fromString(sub)
        } catch (_: IllegalArgumentException) {
            throw BadRequestException("JWT subject (sub) is not a valid UUID.")
        }
    }
}