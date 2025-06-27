package com.range.rpms.common.util;

import com.range.rpms.user.dao.repository.UserRepository;
import com.range.rpms.user.exception.UserNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserContextUtil implements UserContext {
    private final UserRepository userRepository;
    public UserContextUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotFoundException("No authenticated user found");
        }
        return authentication.getName();
    }

    @Override
    public long getCurrentUserId() {
   return userRepository.findByUsername(getCurrentUserName()).orElseThrow(()
           ->new UserNotFoundException("user id not found")).getId();
    }

}
