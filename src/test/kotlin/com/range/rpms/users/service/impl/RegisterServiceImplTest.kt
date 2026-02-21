package com.range.rpms.users.service.impl

import com.range.rpms.common.exception.PasswordEncoderException
import com.range.rpms.users.domain.entity.MockUserCreator
import com.range.rpms.users.domain.entity.User
import com.range.rpms.users.domain.repository.UserRepository
import com.range.rpms.users.dto.RegisterRequest
import com.range.rpms.users.exception.EmailAlreadyUsedException
import com.range.rpms.users.exception.UsernameAlreadyTakenException
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class RegisterServiceImplTest {
    private lateinit var registerService: RegisterServiceImpl
    private lateinit var userRepository: UserRepository
    private lateinit var passwordEncoder: PasswordEncoder
    private lateinit var mockUser: User
    private lateinit var registerRequest: RegisterRequest
    @BeforeEach
    fun setUp() {
        registerRequest= RegisterRequest(
            "testUser",
            "testPassword",
            "testmail@test.com",
        )

        mockUser = MockUserCreator.createMockUser()
        userRepository= Mockito.mock(UserRepository::class.java)
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
        registerService = RegisterServiceImpl(userRepository, passwordEncoder)
    }

    @Test
    fun `Throw exception when username Already Taken`() {
        Mockito.`when`(userRepository.existsByUsername("testUser"))
            .thenReturn(true)

        assertThrows(UsernameAlreadyTakenException::class.java) {
            registerService.register(registerRequest)
        }
    }
    @Test
    fun `Throw Exception when Email Already Used`(){


        Mockito.`when`(userRepository.existsByEmail("testmail@test.com"))
        .thenReturn(true)
        assertThrows(EmailAlreadyUsedException::class.java) {
            registerService.register(registerRequest)
        }
    }
    @Test
    fun `save user when everything ok`(){
        Mockito.`when`(userRepository.existsByUsername("testUser")).thenReturn(false)
        Mockito.`when`(userRepository.existsByEmail("testmail@test.com")).thenReturn(false)
        Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(mockUser)
        assertEquals(registerService.register(registerRequest),mockUser)
    }

    @Test
    fun `throws PasswordEncoderException when encoder returns null`() {
        val encoder = Mockito.mock(PasswordEncoder::class.java)
        val service = RegisterServiceImpl(userRepository, encoder)

        Mockito.`when`(userRepository.existsByUsername(Mockito.anyString())).thenReturn(false)
        Mockito.`when`(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false)

        Mockito.`when`(encoder.encode(Mockito.any()))
            .thenReturn(null)

        assertThrows(PasswordEncoderException::class.java) {
            service.register(registerRequest)
        }
    }
}