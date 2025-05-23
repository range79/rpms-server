package com.range.rpms.friend.service.impl;

import com.range.rpms.common.util.UserContext;
import com.range.rpms.friend.dao.model.FriendRequest;
import com.range.rpms.friend.dao.repository.FriendRequestRepository;
import com.range.rpms.friend.enums.FriendRequestStatus;
import com.range.rpms.friend.exception.FriendRequestUserNotFoundException;
import com.range.rpms.friend.service.FriendRequestActionService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

public class FriendRequestActionServiceImpl implements FriendRequestActionService {
    private FriendRequestRepository friendRequestRepository;
    private UserContext userContext;

    public FriendRequestActionServiceImpl(
            FriendRequestRepository friendRequestRepository,
            UserContext userContext

    ) {
        this.friendRequestRepository= friendRequestRepository;
        this.userContext = userContext;
    }



    @Override
    public void acceptFriendRequest(Long id) {
        FriendRequest friendRequest = friendRequestRepository
                .findById(id).orElseThrow(()->new FriendRequestUserNotFoundException("Friend request not found")
                );
        friendRequest.setStatus(FriendRequestStatus.ACCEPTED);


    }

    @Override
    public void rejectFriendRequest(Long id) {

    }
}
