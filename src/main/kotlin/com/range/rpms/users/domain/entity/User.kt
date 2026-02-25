package com.range.rpms.users.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.SQLRestriction
import java.time.Instant
import java.util.UUID

@Table(name = "users")
@Entity
//@SQLRestriction("account_status <> 'DELETED'")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,
    @Column(nullable = false, unique = true)
    val username: String,
    @Column(nullable = false)
    var password: String,
    @Column(nullable = false, unique = true)
    var email: String,
    @Column(name = "email_verified", nullable = false)
    var emailVerified: Boolean = false,

    @Column(name = "last_login_at")
    var lastLoginAt: Instant? = null,
    @Enumerated(EnumType.STRING)
    val role: Role,
    @Enumerated(EnumType.STRING)
    var accountStatus: AccountStatus,
    var twoFactorEnabled: Boolean = false,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = Instant.now()
)
