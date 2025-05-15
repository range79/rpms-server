package com.range.rpms.service;

import com.range.rpms.dto.user.UserLoginRequest;
import com.range.rpms.dto.user.UserRegisterRequest;
import com.range.rpms.dto.user.UserRegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String login(UserLoginRequest userLoginRequest);
    UserRegisterResponse register (UserRegisterRequest userRegisterRequest);
}
