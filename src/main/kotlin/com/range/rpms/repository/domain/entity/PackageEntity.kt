package com.range.rpms.repository.domain.entity

import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(
    name = "packages",
    indexes = [
        Index(name = "idx_packages_name", columnList = "name")
    ]
)
data class PackageEntity(

    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(nullable = false, unique = true, length = 100)
    val name: String,

    @Column(nullable = false, unique = true, length = 120)
    val slug: String,

    @Column(length = 500)
    var description: String? = null,

    @Column(nullable = false, length = 50)
    var version: String,

    @Column(name = "maintainer_id", nullable = false)
    val maintainerId: UUID,

    @Column(name = "repo_path", nullable = false, length = 500)
    val repoPath: String,


    @Column(name = "stars_count")
    var starsCount: Long = 0,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = Instant.now()
)