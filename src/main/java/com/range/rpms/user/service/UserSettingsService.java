package com.range.rpms.user.service;

import com.range.rpms.packages.enums.PackageVisibility;

public interface UserSettingsService {

    void setDarkMode(boolean darkMode);
    void setEmailNotificationsEnabled(boolean enabled);
    void setDefaultPackageVisibility(PackageVisibility packageVisibility);
    void getDarkMode();
    void getEmailNotificationsEnabled();
    void getDefaultPackageVisibility(PackageVisibility packageVisibility);

}

