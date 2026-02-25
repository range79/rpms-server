package com.range.rpms.repository.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "ssh_keys")
data class SshKey(

    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(nullable = false)
    val userId: UUID,

    @Column(nullable = false, length = 4096)
    val publicKey: String,

    val title: String,

    val revoked: Boolean = false,

    val createdAt: Instant = Instant.now()
)