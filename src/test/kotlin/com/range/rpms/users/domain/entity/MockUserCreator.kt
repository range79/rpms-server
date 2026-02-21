package com.range.rpms.users.domain.entity

import java.util.UUID

class MockUserCreator {
    companion object {
        fun createMockUser(): User {
            return User(
                UUID.randomUUID(),
                "testUser",
                "testPassword",
                email = "testmail@test.com",
                role = Role.USER,
                accountStatus = AccountStatus.ACTIVE,
            )
        }
    }
}