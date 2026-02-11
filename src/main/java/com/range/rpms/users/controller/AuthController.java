package com.range.rpms.users.controller;

import com.range.rpms.common.dto.GenericResponse;
import com.range.rpms.users.api.AuthApi;
import com.range.rpms.users.dto.UserLoginRequest;
import com.range.rpms.users.dto.UserRegisterRequest;
import com.range.rpms.users.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;


@RestController
public class AuthController implements AuthApi {

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    private final AuthService authService;

    @Override
    public ResponseEntity<GenericResponse<String>> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        return ResponseEntity.ok()
            .body(new GenericResponse<>(
                true,
                "User registration successful",
                HttpStatus.OK.value(),
                authService.register(userRegisterRequest)
            ));
    }

    @Override
    public ResponseEntity<GenericResponse<String>> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        return ResponseEntity.ok()
            .body(new GenericResponse<>(
                true,
                "Login successful",
                HttpStatus.OK.value(),
                authService.login(userLoginRequest)
            ));
    }


}
