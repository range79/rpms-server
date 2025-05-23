package com.range.rpms.common.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserContextUtil implements UserContext {
    @Override
    public String getCurrentUserName() {
        return SecurityContextHolder.
                getContext()
                .getAuthentication()
                .getName();
    }
}
