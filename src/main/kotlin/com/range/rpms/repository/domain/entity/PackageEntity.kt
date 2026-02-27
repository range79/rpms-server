package com.range.rpms.repository.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.annotations.UuidGenerator
import java.time.Instant
import java.util.*

@Entity
@Table(
    name = "packages",
    indexes = [
        Index(name = "idx_packages_name", columnList = "name"),
        Index(name = "idx_packages_slug", columnList = "slug")
    ],
    uniqueConstraints = [
        UniqueConstraint(name = "uk_packages_slug", columnNames = ["slug"])
    ]
)
data class PackageEntity(

    @Id
    @UuidGenerator
    @Column(columnDefinition = "uuid")
    var id: UUID? = null,

    @Column(nullable = false, length = 100)
    var name: String = "",

    @Column(nullable = false, length = 120)
    var slug: String = "",

    @Column(length = 500)
    var description: String? = null,

    @Column(nullable = false, length = 50)
    var version: String = "",

    @Column(name = "maintainer_id", nullable = false, columnDefinition = "uuid")
    var maintainerId: UUID? = null,

    @Column(name = "repo_path", nullable = false, length = 500)
    var repoPath: String = "",

    @Column(name = "stars_count", nullable = false)
    var starsCount: Long = 0,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    var createdAt: Instant? = null,

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: Instant? = null,

    @Version
    var rowVersion: Long = 0
)