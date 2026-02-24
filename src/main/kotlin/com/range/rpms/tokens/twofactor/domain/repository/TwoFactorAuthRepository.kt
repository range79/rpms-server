package com.range.rpms.tokens.twofactor.domain.repository

import com.range.rpms.tokens.twofactor.domain.entity.TwoFactorAuthToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TwoFactorAuthRepository : CrudRepository<TwoFactorAuthToken, String> {
}