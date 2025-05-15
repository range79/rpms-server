package com.range.rpms.dto.user;

import com.range.rpms.dao.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterResponse {
    private String id;
    private String username;
    private Role role;
}
