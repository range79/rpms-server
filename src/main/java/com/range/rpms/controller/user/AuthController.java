package com.range.rpms.controller.user;

import com.range.rpms.dto.GenericResponse;
import com.range.rpms.dto.user.UserLoginRequest;
import com.range.rpms.dto.user.UserRegisterRequest;
import com.range.rpms.dto.user.UserRegisterResponse;
import com.range.rpms.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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
    @Operation(summary = "register",
            description = "register a user to database"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User register is successful",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponse.class),
                            examples = @ExampleObject(value = """
                                {
                                  "success": true,
                                  "message": "User register is successful",
                                  "statusCode": 200,
                                  "data": {
                                        "id": "your-id",
                                        "username": "your-username",
                                        "role": "user"
                                    }
                                    }
                    
                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "user already exits",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponse.class),
                            examples = @ExampleObject(value = """
                {
                  "success": false,
                  "message": "user already exits",
                  "statusCode": 409,
                  "data": null
                  }
                """
                            )
                    )
            )
    })
    @PostMapping("/register")
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

    @Operation(summary = "Login", description = "Authenticates user and returns JWT token")
    @ApiResponses(
            value = {

                    @ApiResponse(
                            responseCode = "200",
                            description = "Login successful",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = GenericResponse.class),
                                    examples = @ExampleObject(value = """
                                {
                                  "success": true,
                                  "message": "User login is successful",
                                  "statusCode": 200,
                                  "data": "jwt.token.here"
                                }
                                """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Wrong password",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = GenericResponse.class),
                                    examples = @ExampleObject(value = """
                                {
                                  "success": false,
                                  "message": "wrong password",
                                  "statusCode": 401,
                                  "data": null
                                }
                                """)
                            )
                    )
            }
    )

    @PostMapping("/login")
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
