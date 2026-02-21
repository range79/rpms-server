package com.range.rpms.token.domain.repository

import com.range.rpms.token.domain.entity.Token
import org.springframework.data.repository.CrudRepository

interface TokenRepository : CrudRepository<Token, String> {
}