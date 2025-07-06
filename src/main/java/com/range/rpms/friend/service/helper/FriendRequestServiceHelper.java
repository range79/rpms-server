package com.range.rpms.friend.service.helper;

import com.range.rpms.common.util.UserContext;
import com.range.rpms.common.util.UserContextUtil;
import com.range.rpms.user.dao.model.User;
import com.range.rpms.user.dao.repository.UserRepository;

import com.range.rpms.user.exception.UserNotFoundException;
import com.range.rpms.user.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class FriendRequestServiceHelper {


    private final UserContext currentUser;
private final UserService userService;
    public FriendRequestServiceHelper(UserService userService, UserContext currentUser) {
      this.userService = userService;
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        Long currentUsername = currentUser.getCurrentUserId();
       return userService.findUser(currentUsername);
    }

    public User getRecevierUserById(Long id) {
        return userService.findUser(id);
    }




}
