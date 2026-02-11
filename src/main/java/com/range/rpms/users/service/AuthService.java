package com.range.rpms.users.service;

import com.range.rpms.users.dto.UserLoginRequest;
import com.range.rpms.users.dto.UserRegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String login(UserLoginRequest userLoginRequest);
    String register (UserRegisterRequest userRegisterRequest);
}
