package com.range.rpms.friend.service;

import com.range.rpms.friend.domain.model.Friend;

public interface FriendRequestActionService {
    void acceptFriendRequest(Long id);
    void rejectFriendRequest(Long id);
    void removeFriend(Long id);

}
