package com.range.rpms.controller.user;

import com.range.rpms.dto.ApiResponse;
import com.range.rpms.dto.user.UserLoginRequest;
import com.range.rpms.dto.user.UserRegisterRequest;
import com.range.rpms.dto.user.UserRegisterResponse;
import com.range.rpms.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserRegisterResponse>> register(@RequestBody UserRegisterRequest userRegisterRequest) {

        return
                ResponseEntity.ok(new ApiResponse<>(
                        true,
                        "User login is successful",
                        HttpStatus.OK.value(),
                        authService.register(userRegisterRequest)
                )
                );

    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody UserLoginRequest userLoginRequest) {

        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "User login is successful",
                HttpStatus.OK.value(),
                authService.login(userLoginRequest)
        )
        );

    }

}
