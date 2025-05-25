package com.range.rpms.user.service;

import com.range.rpms.user.dto.UserLoginRequest;
import com.range.rpms.user.dto.UserRegisterRequest;
import com.range.rpms.user.dto.UserRegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String login(UserLoginRequest userLoginRequest);
    UserRegisterResponse register (UserRegisterRequest userRegisterRequest);
}
