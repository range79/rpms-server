package com.range.rpms.user.controller;

import com.range.rpms.common.dto.GenericResponse;
import com.range.rpms.user.api.AuthApi;
import com.range.rpms.user.dto.UserLoginRequest;
import com.range.rpms.user.dto.UserRegisterRequest;
import com.range.rpms.user.dto.UserRegisterResponse;
import com.range.rpms.user.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthApi {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public ResponseEntity<GenericResponse<UserRegisterResponse>> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {

        return
                ResponseEntity.ok(new GenericResponse<>(
                                true,
                                "User register is successful",
                                HttpStatus.OK.value(),
                                authService.register(userRegisterRequest)
                        )
                );

    }



    public ResponseEntity<GenericResponse<String>> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {

        return ResponseEntity.ok(new GenericResponse<>(
                        true,
                        "User login is successful",
                        HttpStatus.OK.value(),
                        authService.login(userLoginRequest)
                )
        );

    }

}
