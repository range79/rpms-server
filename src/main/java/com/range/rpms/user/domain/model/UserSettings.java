package com.range.rpms.user.domain.model;

import com.range.rpms.packages.enums.PackageVisibility;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_settings")
public class UserSettings {
    @Id
    private long id;
    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    private boolean darkMode;
    private boolean receiveEmail;
    @Enumerated(EnumType.STRING)
    private PackageVisibility defaultPackageVisibility;

}
