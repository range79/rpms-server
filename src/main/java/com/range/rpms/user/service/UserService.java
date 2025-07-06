package com.range.rpms.user.service;

import com.range.rpms.user.domain.model.User;

public interface UserService {
    User findUser(Long id);
    User findUserByUsername(String username);

}
