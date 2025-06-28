package com.range.rpms.user.dao.repository;

import com.range.rpms.user.dao.model.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> {
}
