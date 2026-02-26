package com.range.rpms.repository.dto
data class AddSshKeyRequest(
    val title: String,
    val publicKey: String
)