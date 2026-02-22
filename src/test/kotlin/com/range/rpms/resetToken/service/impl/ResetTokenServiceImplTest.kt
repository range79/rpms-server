package com.range.rpms.resetToken.service.impl

import com.range.rpms.registerToken.domain.entity.ResetToken
import com.range.rpms.registerToken.domain.repository.ResetTokenRepository
import com.range.rpms.registerToken.exception.ResetTokenLimitExceededException
import com.range.rpms.registerToken.properties.ResetTokenProperties
import com.range.rpms.registerToken.service.impl.ResetTokenServiceImpl
import org.hamcrest.CoreMatchers.any
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
import java.util.Optional
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class ResetTokenServiceImplTest {
    private lateinit var service: ResetTokenServiceImpl
    private lateinit var repository: ResetTokenRepository
    private lateinit var properties: ResetTokenProperties
    private lateinit var resetToken: ResetToken

    @BeforeEach
    fun setUp() {
        resetToken = ResetToken(
            "string",
            "test",
            123
        )
        properties = ResetTokenProperties(30, 3)
        repository = mock(ResetTokenRepository::class.java)
        service = ResetTokenServiceImpl(repository, properties)
    }

    @Test
    fun `create new token when limit not exceeded`() {
        Mockito.`when`(repository.countByEmail("test")).thenReturn(0)
        Mockito.`when`(repository.save(Mockito.any(ResetToken::class.java)))
            .thenReturn(resetToken)

        assertEquals(resetToken, service.createToken("test"))

    }

    @Test
    fun `dont create new token when limit exceeded`() {
        Mockito.`when`(repository.countByEmail("test")).thenReturn(4)
        assertThrows(ResetTokenLimitExceededException::class.java) {
            service.createToken("test")
        }
    }

    @Test
    fun `return false when token not exists`() {
        Mockito.`when`(repository.findById("test")).thenReturn(Optional.ofNullable(null))
        assertEquals(false, service.validateToken("test"))
    }
    @Test
    fun `return true when token exists`() {
        Mockito.`when`(repository.findById("test")).thenReturn(Optional.of(resetToken))

        assertEquals(true, service.validateToken("test"))

        Mockito.verify(repository).delete(resetToken)
    }

}