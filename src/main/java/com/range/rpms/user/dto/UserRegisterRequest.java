package com.range.rpms.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UserRegisterRequest {

    private String username;
    @Size(min = 8,message = "min 8 character required")
    private String password;
    @Email
    private String email;

}
