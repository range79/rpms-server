package com.range.rpms.friend.service.impl;

import com.range.rpms.common.util.UserContext;
import com.range.rpms.friend.domain.model.Friend;
import com.range.rpms.friend.domain.model.FriendRequest;
import com.range.rpms.friend.domain.repository.FriendRepository;
import com.range.rpms.friend.exception.FriendNotFoundException;
import com.range.rpms.friend.service.FriendRequestActionService;
import com.range.rpms.friend.service.FriendRequestService;
import com.range.rpms.user.domain.model.User;
import com.range.rpms.user.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class FriendRequestActionServiceImpl implements FriendRequestActionService {


    private final FriendRequestService friendRequestService;
    private final UserContext userContext;
    private final  FriendRepository friendRepository;
    private final UserService  userService;


    public FriendRequestActionServiceImpl(
            FriendRequestService friendRequestService,
            UserContext userContext,
            FriendRepository friendRepository,
            UserService userService
    ) {
        this.friendRequestService = friendRequestService;
        this.userContext = userContext;
        this.friendRepository = friendRepository;
        this.userService = userService;

    }


    @Override
    @Transactional
    public void acceptFriendRequest(Long id) {
        FriendRequest friendRequest =friendRequestService.findFriend(id);
        User sender = friendRequest.getSender();
        User receiver = friendRequest.getReceiver();

        if (receiver.getId()!=getUserId()) {
            throw new AccessDeniedException("You cannot accept a request not sent to you.");
        }


        Friend friend = new Friend(sender, receiver);

        friendRepository.save(friend);
        friendRequestService.deleteFriendRequest(id);
    }
    @Transactional
    @Override
    public void rejectFriendRequest(Long id) {
        friendRequestService.deleteFriendRequest(id);
    }


    @Override
    @Transactional
    public void removeFriend(Long id) {

        User user1 = userService.findUser(getUserId());
        User user2 = userService.findUser(id);
        friendRepository
                .deleteFriendBySenderAndReceiverOrSenderAndReceiver
                        (user1,user2,
                                user2,user1);
    }

    @Override
    public Friend findFriend(Long id) {

        return  friendRepository
                .findById(id)
                .orElseThrow(()->new FriendNotFoundException("Friend not found:"+id));
    }

    private long getUserId(){
        return userContext.getCurrentUserId();
    }
}

