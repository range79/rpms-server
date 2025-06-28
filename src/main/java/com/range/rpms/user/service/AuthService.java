package com.range.rpms.user.service;

import com.range.rpms.user.dto.UserLoginRequest;
import com.range.rpms.user.dto.UserRegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String login(UserLoginRequest userLoginRequest);
    String register (UserRegisterRequest userRegisterRequest);
}
