package com.range.rpms.user.dao.model;

import com.range.rpms.user.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private Role role;


}
