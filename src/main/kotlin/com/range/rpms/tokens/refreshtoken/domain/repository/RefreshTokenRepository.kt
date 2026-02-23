package com.range.rpms.tokens.refreshtoken.domain.repository

import com.range.rpms.tokens.refreshtoken.domain.entity.RefreshToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshToken, String>{
    fun deleteAllByUserIdAndFamilyId(userId: String, familyId: String)
    fun deleteAllByUserId(userId: String)

}
