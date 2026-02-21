package com.range.rpms.resetToken.service.impl

import com.range.rpms.registerToken.domain.entity.ResetToken
import com.range.rpms.registerToken.domain.repository.ResetTokenRepository
import com.range.rpms.registerToken.properties.ResetTokenProperties
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ResetTokenServiceImplTest {
private lateinit var service: ResetTokenServiceImpl
private lateinit var repository: ResetTokenRepository
private lateinit var properties: ResetTokenProperties
private lateinit var registerToken: ResetToken

@BeforeEach
    fun setUp() {
        properties = ResetTokenProperties(30)
        repository = mock(ResetTokenRepository::class.java)
        service = ResetTokenServiceImpl(repository, properties)
    }

    @Test
    fun `create new token` () {
        Mockito.`when`(repository.sa)



    }

    @Test
    fun validateToken() {
    }

}