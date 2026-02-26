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
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
@RestController
class SshKeyController(
    private val sshKeyService: SshKeyService
) : SshKeyApi {
    override fun add(
        @AuthenticationPrincipal jwt: Jwt,
        @Valid req: AddSshKeyRequest
    ): ResponseEntity<SshKeyResponse> {
        val userId = jwtUserId(jwt)
        val created = sshKeyService.addKey(userId, req)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }

    override fun list(
        @AuthenticationPrincipal jwt: Jwt,
        pageable: Pageable
    ): ResponseEntity<Page<SshKeyResponse>> {
        val userId = jwtUserId(jwt)
        return ResponseEntity.ok(sshKeyService.listActiveKeys(userId, pageable))
    }



    override fun revoke(
        @AuthenticationPrincipal jwt: Jwt,
        id: UUID
    ): ResponseEntity<Void> {
        val userId = jwtUserId(jwt)
        sshKeyService.revoke(id, userId)
        return ResponseEntity.noContent().build()
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