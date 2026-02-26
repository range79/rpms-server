package com.range.rpms.repository.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UuidGenerator
import java.time.Instant
import java.util.*

@Entity
@Table(
    name = "ssh_keys",
    uniqueConstraints = [
        UniqueConstraint(
            name = "uk_ssh_user_fingerprint",
            columnNames = ["user_id", "fingerprint"]
        )
    ]
)
data class SshKeyEntity(

    @Id
    @UuidGenerator
    @Column(columnDefinition = "uuid")
    var id: UUID? = null,

    @Column(name = "user_id", nullable = false, columnDefinition = "uuid")
    var userId: UUID? = null,

    @Column(nullable = false, length = 4096)
    var publicKey: String = "",

    @Column(nullable = false, length = 64)
    var fingerprint: String = "",

    @Column(nullable = false, length = 120)
    var title: String = "",

    @Column(nullable = false)
    var revoked: Boolean = false,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    var createdAt: Instant? = null
)