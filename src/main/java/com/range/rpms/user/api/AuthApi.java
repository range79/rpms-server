package com.range.rpms.user.api;

import com.range.rpms.common.dto.GenericResponse;
import com.range.rpms.user.dto.UserLoginRequest;
import com.range.rpms.user.dto.UserRegisterRequest;
import com.range.rpms.user.dto.UserRegisterResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthApi  {
    @Operation(
            summary = "resgister",
            description = "register to the app "
    )
    @PostMapping("/register")
    ResponseEntity<GenericResponse<UserRegisterResponse>> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest);
    @Operation(
            summary = "login",
            description = "login to the app "
    )
    @PostMapping("/login")
    ResponseEntity<GenericResponse<String>> login(@RequestBody @Valid UserLoginRequest userLoginRequest);
}
