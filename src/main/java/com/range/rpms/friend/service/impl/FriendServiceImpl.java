package com.range.rpms.friend.service.impl;

import com.range.rpms.friend.domain.model.Friend;
import com.range.rpms.friend.domain.repository.FriendRepository;
import com.range.rpms.friend.service.FriendService;
import com.range.rpms.user.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;

    public FriendServiceImpl(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @Override
    public List<Friend> getFriends(User user) {

       return  friendRepository.findFriendBySenderOrReceiver(user, user);

    }


}
