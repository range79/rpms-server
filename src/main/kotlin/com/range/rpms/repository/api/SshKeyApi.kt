package com.range.rpms.repository.api

import com.range.rpms.repository.dto.AddSshKeyRequest
import com.range.rpms.repository.dto.SshKeyResponse
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RequestMapping("/api/v1/ssh-keys")
interface SshKeyApi {

    @PostMapping
    fun add(
        @RequestAttribute("jwt") jwt: Jwt,
        @Valid @RequestBody req: AddSshKeyRequest
    ): SshKeyResponse

    @GetMapping
    fun list(
        @RequestAttribute("jwt") jwt: Jwt,
        pageable: Pageable
    ): Page<SshKeyResponse>

    @DeleteMapping("/{id}")
    fun revoke(
        @RequestAttribute("jwt") jwt: Jwt,
        @PathVariable id: UUID
    )
}