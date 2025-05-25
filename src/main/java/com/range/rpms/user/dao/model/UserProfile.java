package com.range.rpms.user.dao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_profile")
@Getter
@Setter
public class UserProfile {
    @Id
    private long id;
    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(length= 200)
    private String bio;

    private String avatarUrl;

    private LocalDate birthDate;

    private String location;

    private String github;

    private String website;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
