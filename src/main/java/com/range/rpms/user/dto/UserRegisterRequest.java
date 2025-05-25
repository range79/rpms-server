package com.range.rpms.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {

    private String username;
    private String password;
    private String email;

}
