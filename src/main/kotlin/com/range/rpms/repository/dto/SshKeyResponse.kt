package com.range.rpms.repository.dto

import java.util.UUID

data class SshKeyResponse(
    val id: UUID,
    val title: String,
    val fingerprint: String,
    val revoked: Boolean
)