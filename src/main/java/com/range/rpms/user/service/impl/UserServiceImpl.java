package com.range.rpms.user.service.impl;

import com.range.rpms.user.domain.model.User;
import com.range.rpms.user.domain.repository.UserRepository;
import com.range.rpms.user.exception.UserNotFoundException;
import com.range.rpms.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public User findUser(Long id) {
     return userRepository.findById(id).orElseThrow((()->
             new UserNotFoundException("User not found")));
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->
                new UserNotFoundException("User not found"));
    }
}
