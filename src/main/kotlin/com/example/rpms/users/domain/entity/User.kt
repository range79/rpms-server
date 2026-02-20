package com.example.rpms.users.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID
@Table(name = "user")
@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID,
    val username: String,
    var password: String,
    var email: String,
)
