package com.range.rpms.users.service.impl;

import com.range.rpms.users.domain.model.User;
import com.range.rpms.users.domain.repository.UserRepository;
import com.range.rpms.users.exception.UserNotFoundException;
import com.range.rpms.users.service.UserService;
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
