package com.range.rpms.users.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
