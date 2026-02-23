package com.range.rpms.users.service.impl

import com.range.rpms.users.domain.entity.MockUserCreator
import com.range.rpms.users.domain.entity.User
import com.range.rpms.users.domain.repository.UserRepository
import com.sun.source.tree.ModuleTree
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class UserServiceImplTest {

    private lateinit var userRepository: UserRepository
    private lateinit var userService: UserServiceImpl
    private lateinit var mockUser: User


    @BeforeEach
    fun setup() {
        mockUser = MockUserCreator.createMockUser();
        userRepository = mock(UserRepository::class.java)
        userService = UserServiceImpl(userRepository)

    }


    @Test
    fun `return null when username not found`() {
        Mockito.`when`(userRepository.findByUsername("test")).thenReturn(null)
        assertEquals(null, userService.findByUsername("test"))
    }


    @Test
    fun `return user when username not found`() {
        Mockito.`when`(userRepository.findByUsername("test")).thenReturn(mockUser)
        assertEquals(mockUser,userService.findByUsername("test"))
    }

    @Test
    fun `return null when email not found`() {
        Mockito.`when`(userRepository.findByEmail("test")).thenReturn(null)
        assertEquals(null, userService.findByEmail("test"))
    }
    @Test
    fun `return user when email found`(){
        Mockito.`when`(userRepository.findByEmail("test")).thenReturn(mockUser)
        assertEquals(mockUser,userService.findByEmail("test"))
    }
    @Test
    fun `return null when username not found and email not found`() {
        Mockito.`when`(userRepository.findByUsername("test")).thenReturn(null)
        Mockito.`when`(userRepository.findByEmail("test")).thenReturn(null)
        assertEquals(null, userService.findByEmailORUsername("test"))
    }

}