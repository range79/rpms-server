package com.range.rpms.registerToken.domain.repository

import com.range.rpms.registerToken.domain.entity.ResetToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ResetTokenRepository : CrudRepository<ResetToken, String> {
    fun countByEmail(email: String): Long
}