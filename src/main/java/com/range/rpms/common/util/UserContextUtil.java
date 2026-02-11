package com.range.rpms.common.util;

import com.range.rpms.common.security.CustomUserDetails;
import com.range.rpms.users.exception.UserNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserContextUtil implements UserContext {



    @Override
    public long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotFoundException("No authenticated user found");
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails customUserDetails) {
            return customUserDetails.getId();
        }
        throw new UserNotFoundException("Invalid user principal");
    }


}
