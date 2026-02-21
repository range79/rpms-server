package com.range.rpms.users.service.impl

import com.range.rpms.users.domain.entity.AccountStatus
import com.range.rpms.users.domain.entity.MockUserCreator
import com.range.rpms.users.domain.entity.User
import com.range.rpms.users.dto.LoginRequest
import com.range.rpms.users.exception.AccountBannedException
import com.range.rpms.users.exception.AccountSuspendedException
import com.range.rpms.users.exception.AuthenticationException
import com.range.rpms.users.service.UserService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user

@ExtendWith(MockitoExtension::class)
class LoginServiceImplTest {

    private lateinit var loginService: LoginServiceImpl
    private lateinit var userService: UserService
    private lateinit var passwordEncoder: PasswordEncoder
    private lateinit var loginRequest: LoginRequest
    private lateinit var user: User
    @BeforeEach
    fun setUp() {

        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
        user = MockUserCreator.createMockUser().apply {

            this.password = passwordEncoder.encode("test")!!

        }
        userService = Mockito.mock(UserService::class.java)
        loginService = LoginServiceImpl(userService, passwordEncoder)
        loginRequest = LoginRequest("test", "test")

    }

    @Test
    fun `throw exception if user not found`() {
        Mockito.`when`(userService.findByEmailORUsername("test")).thenReturn(null)
        assertThrows(AuthenticationException::class.java) {
            loginService.login(loginRequest)
        }
    }
    @Test
    fun `when user found and account is activated try to login`() {

        Mockito.`when`(userService.findByEmailORUsername("test")).thenReturn(user)
        assertEquals(user,loginService.login(loginRequest))
    }
    @Test
    fun `throw exception when account banned`(){
        Mockito.`when`(userService.findByEmailORUsername("test")).thenReturn(user.apply {
            accountStatus= AccountStatus.BANNED
        })
        assertThrows(AccountBannedException::class.java) {
            loginService.login(loginRequest)
        }
    }
    @Test
    fun `throw exception when account suspended`(){
        Mockito.`when`(userService.findByEmailORUsername("test")).thenReturn(user.apply {
            accountStatus= AccountStatus.SUSPENDED
        })
        assertThrows(AccountSuspendedException::class.java) {
            loginService.login(loginRequest)
        }
    }
    @Test
    fun `throw exception when password invalid`(){

        Mockito.`when`(userService.findByEmailORUsername("test")).thenReturn(user)
        loginRequest.apply { password = passwordEncoder.encode("test1")!!}
        assertThrows(AuthenticationException::class.java) {
            loginService.login(loginRequest)
        }
    }


}