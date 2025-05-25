package com.range.rpms.user.dto;

import com.range.rpms.user.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterResponse {
    private Long id;
    private String username;
    private Role role;
}
