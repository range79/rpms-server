package com.range.rpms.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.mapstruct.EnumMapping;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.annotation.Documented;
import java.util.Collection;
import java.util.List;

@Document(collection = "packages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User  {
    private String id;
    @Indexed(unique = true)
    private String username;
    private String password;
    @Indexed(unique = true)
    private String email;
    private Role role;
}
