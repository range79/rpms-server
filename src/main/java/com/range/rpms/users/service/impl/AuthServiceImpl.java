package com.range.rpms.users.service.impl;

import com.range.rpms.users.enums.Role;
import com.range.rpms.users.domain.model.User;
import com.range.rpms.users.domain.repository.UserRepository;
import com.range.rpms.users.dto.UserLoginRequest;
import com.range.rpms.users.dto.UserRegisterRequest;
import com.range.rpms.users.exception.AuthenticationFailedException;
import com.range.rpms.users.exception.UserAlreadyExistsException;
import com.range.rpms.users.exception.UserNotFoundException;
import com.range.rpms.users.mapper.UserMapper;
import com.range.rpms.users.service.AuthService;
import com.range.rpms.common.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    @Override
    public String login(UserLoginRequest userLoginRequest) {

        User user = userRepository
                .findByUsername(userLoginRequest.getUsername())
                .orElseThrow(()->
                        new UserNotFoundException("User not found")
                );

        if (!passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {

            throw new AuthenticationFailedException("Authentication failed: incorrect password.");

        }

        logger.info("User '{}' successfully logged in", userLoginRequest.getUsername());

        return jwtUtil.generateToken(user.getUsername(), user.getRole());

    }
    @Transactional
    @Override
    public String register(UserRegisterRequest userRegisterRequest) {


        if (userRepository.existsByUsername(userRegisterRequest.getUsername())) {

            throw new UserAlreadyExistsException("This username is already taken");

        }

        if (userRepository.existsByEmail(userRegisterRequest.getEmail())) {

            throw new UserAlreadyExistsException("This email is already taken");

        }

        userRegisterRequest.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));

        User user = userMapper.userRegisterRequestToUser(userRegisterRequest);

        user.setRole(Role.ROLE_USER);

        userRepository.save(user);

        logger.info("New user '{}' registered with email '{}'", user.getUsername(), user.getEmail());

        return jwtUtil.generateToken(user.getUsername(), user.getRole());


    }
}
