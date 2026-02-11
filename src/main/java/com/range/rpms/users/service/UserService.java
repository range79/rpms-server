package com.range.rpms.users.service;

import com.range.rpms.users.domain.model.User;

public interface UserService {
    User findUser(Long id);
    User findUserByUsername(String username);

}
