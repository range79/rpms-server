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
import java.util.UUID
@Table(name = "user")
@Entity
//@SQLRestriction("account_status <> 'DELETED'")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?=null,
    @Column(nullable = false,unique = true)
    val username: String,
    @Column(nullable = false)
    var password: String,
    @Column(nullable = false,unique = true)
    var email: String,
    @Enumerated(EnumType.STRING)
    val role: Role,
    @Enumerated(EnumType.STRING)
    val accountStatus: AccountStatus
)
