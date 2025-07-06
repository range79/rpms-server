package com.range.rpms.user.service;

import com.range.rpms.user.dao.model.User;
import jakarta.persistence.Id;

public interface UserService {
    User findUser(Long id);
    User findUserByUsername(String username);

}
