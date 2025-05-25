package com.range.rpms.friend.service.impl;

import com.range.rpms.common.util.UserContext;
import com.range.rpms.friend.dao.model.Friend;
import com.range.rpms.friend.dao.model.FriendRequest;
import com.range.rpms.friend.dao.repository.FriendRepository;
import com.range.rpms.friend.dao.repository.FriendRequestRepository;
import com.range.rpms.friend.exception.FriendRequestUserNotFoundException;
import com.range.rpms.friend.service.FriendRequestActionService;
import com.range.rpms.user.dao.model.User;
import com.range.rpms.user.dao.repository.UserRepository;
import com.range.rpms.user.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class FriendRequestActionServiceImpl implements FriendRequestActionService {

// todo this service needs big refactor

    private final FriendRequestRepository friendRequestRepository;
    private final UserContext userContext;
    private final  FriendRepository friendRepository;
    private final UserRepository userRepository;
    public FriendRequestActionServiceImpl(
            FriendRequestRepository friendRequestRepository,
            UserContext userContext,
            FriendRepository friendRepository,
            UserRepository userRepository

    ) {
        this.friendRequestRepository= friendRequestRepository;
        this.userContext = userContext;
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public void acceptFriendRequest(Long id) {
        FriendRequest friendRequest = friendRequestRepository
                .findById(id)
                .orElseThrow(() -> new FriendRequestUserNotFoundException("Friend request not found"));


        String currentUsername = userContext.getCurrentUserName();


        User sender = friendRequest.getSender();
        User receiver = friendRequest.getReceiver();

        if (!receiver.getUsername().equals(currentUsername)) {
            throw new AccessDeniedException("You cannot accept a request not sent to you.");
        }


        Friend friend = new Friend(sender, receiver);


        friendRepository.save(friend);


        friendRequestRepository.delete(friendRequest);
    }
    @Transactional
    @Override
    public void rejectFriendRequest(Long id) {

        friendRequestRepository.delete(friendRequestRepository.findById(id).orElseThrow(()->new FriendRequestUserNotFoundException("Friend request not found")));
    }


    @Override
    @Transactional
    public void removeFriend(Long id) {
        String currentUsername = userContext.getCurrentUserName();
        User user1 = userRepository.findByUsername(currentUsername).orElseThrow(()->new UserNotFoundException("User not found"));
        User user2 = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
     friendRepository
             .deleteFriendBySenderAndReceiverOrSenderAndReceiver
                     (user1,user2,
             user2,user1);
    }





}

