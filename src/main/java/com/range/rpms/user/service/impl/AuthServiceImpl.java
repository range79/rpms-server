package com.range.rpms.user.service.impl;

import com.range.rpms.user.enums.Role;
import com.range.rpms.user.dao.model.User;
import com.range.rpms.user.dao.repository.UserRepository;
import com.range.rpms.user.dto.UserLoginRequest;
import com.range.rpms.user.dto.UserRegisterRequest;
import com.range.rpms.user.dto.UserRegisterResponse;
import com.range.rpms.user.exception.AuthenticationFailedException;
import com.range.rpms.user.exception.UserAlreadyExistsException;
import com.range.rpms.user.exception.UserNotFoundException;
import com.range.rpms.user.mapper.UserMapper;
import com.range.rpms.user.service.AuthService;
import com.range.rpms.common.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;
    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil,
                           UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
    }

    @Override
    public String login(UserLoginRequest userLoginRequest) {

        User user = userRepository
                .findByUsername(userLoginRequest.getUsername())
                .orElseThrow(()->
                        new UserNotFoundException("User not found")
                );

        if (!passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {

            throw new AuthenticationFailedException("Wrong password");

        }

        logger.info("User '{}' successfully logged in", userLoginRequest.getUsername());

        return jwtUtil.generateToken(user.getUsername(), user.getRole());

    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {


        if (userRepository.existsByUsername(userRegisterRequest.getUsername())) {

            throw new UserAlreadyExistsException("This username already taken");

        }
        //if users email exits throw an exception
        if (userRepository.existsByEmail(userRegisterRequest.getEmail())) {

            throw new UserAlreadyExistsException("This email already taken");

        }

        userRegisterRequest.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));

        User user = userMapper.userRegisterRequestToUser(userRegisterRequest);

        user.setRole(Role.ROLE_USER);

        userRepository.save(user);

        logger.info("New user '{}' registered with email '{}'", user.getUsername(), user.getEmail());

        return userMapper.userToUserRegisterResponse(user);


    }
}
