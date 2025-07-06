package com.range.rpms.user.service.impl;

import com.range.rpms.packages.enums.PackageVisibility;
import com.range.rpms.user.domain.model.UserSettings;
import com.range.rpms.user.domain.repository.UserSettingsRepository;
import com.range.rpms.user.service.UserSettingsService;
import org.springframework.stereotype.Service;

@Service
public class UserSettingsServiceImpl implements UserSettingsService {

    private final UserSettingsRepository userSettingsRepository;



    public UserSettingsServiceImpl(UserSettingsRepository userSettingsRepository) {
        this.userSettingsRepository = userSettingsRepository;

    }


    @Override
    public void setDarkMode(boolean darkMode) {
        UserSettings userSettings = new UserSettings();
        userSettings.setDarkMode(darkMode);
        userSettingsRepository.save(userSettings);
    }

    @Override
    public void setEmailNotificationsEnabled(boolean enabled) {
        UserSettings userSettings = new UserSettings();
        userSettings.setReceiveEmail(enabled);
        userSettingsRepository.save(userSettings);
    }

    @Override
    public void setDefaultPackageVisibility(PackageVisibility packageVisibility) {
        UserSettings userSettings = new UserSettings();
        userSettings.setDefaultPackageVisibility(packageVisibility);
        userSettingsRepository.save(userSettings);
    }

    @Override
    public void getDarkMode() {
        UserSettings userSettings =new UserSettings();
        userSettings.setDarkMode(true);
        userSettingsRepository.save(userSettings);
    }

    @Override
    public void getEmailNotificationsEnabled() {
        UserSettings userSettings =new UserSettings();
        userSettings.setReceiveEmail(true);
        userSettingsRepository.save(userSettings);
    }

    @Override
    public void getDefaultPackageVisibility(PackageVisibility packageVisibility) {
    UserSettings userSettings =new UserSettings();
    userSettings.setDefaultPackageVisibility(packageVisibility);
    userSettingsRepository.save(userSettings);
    }

}
