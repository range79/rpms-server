package com.range.rpms.user.controller;

import com.range.rpms.common.dto.GenericResponse;
import com.range.rpms.user.api.AuthApi;
import com.range.rpms.user.dto.UserLoginRequest;
import com.range.rpms.user.dto.UserRegisterRequest;
import com.range.rpms.user.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

/*
    l will add in new version
    @Value("${app.jwt-duration}")
    private int jwtDuration;
    @Value("${app.https}")
    private boolean httpEnable;
    public ResponseEntity<GenericResponse<Void>> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        String token = authService.register(userRegisterRequest);
        ResponseCookie cookie = ResponseCookie.from("auth", token)
                .httpOnly(true)
                .secure(httpEnable)
                .path("/")
                .maxAge(jwtDuration)
                .sameSite("Strict")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new GenericResponse<>(
                        true,
                        "User registration successful",
                        HttpStatus.OK.value(),
                        null
                ));


    }
    public ResponseEntity<GenericResponse<Void>> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        String token = authService.login(userLoginRequest);
        ResponseCookie cookie = ResponseCookie.from("auth", token)
                .httpOnly(true)
                .secure(httpEnable)
                .path("/")
                .maxAge(jwtDuration)
                .sameSite("Strict")
                .build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,cookie.toString()).body(new GenericResponse<>(
                        true,
                        "User login is successful",
                        HttpStatus.OK.value(),
                        null
                )
        );

    }
*/
}
