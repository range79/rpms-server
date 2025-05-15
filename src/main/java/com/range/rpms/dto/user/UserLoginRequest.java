package com.range.rpms.dto.user;

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
