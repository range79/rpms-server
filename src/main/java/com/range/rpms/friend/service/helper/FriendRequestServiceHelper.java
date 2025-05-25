package com.range.rpms.friend.service.helper;

import com.range.rpms.common.util.UserContext;
import com.range.rpms.common.util.UserContextUtil;
import com.range.rpms.user.dao.model.User;
import com.range.rpms.user.dao.repository.UserRepository;

import com.range.rpms.user.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class FriendRequestServiceHelper {

    private UserRepository userRepository;
    private UserContext currentUser;

    public FriendRequestServiceHelper(UserRepository userRepository, UserContext currentUser) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        String currentUsername = currentUser.getCurrentUserName();
       return userRepository.findByUsername(currentUsername)
               .orElseThrow(()->
                       new UserNotFoundException("Sender not found"));
    }

    public User getRecevierUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(()
                        ->new UserNotFoundException("Receiver not found"));
    }

    public String getCurrentUsername() {
        return currentUser.getCurrentUserName();
    }


}
