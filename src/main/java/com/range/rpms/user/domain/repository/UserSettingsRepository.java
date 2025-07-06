package com.range.rpms.user.domain.repository;

import com.range.rpms.user.domain.model.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> {
}
