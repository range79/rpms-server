package com.range.rpms.friend.service;

public interface FriendRequestActionService {
    void acceptFriendRequest(Long id);
    void rejectFriendRequest(Long id);
    void removeFriend(Long id);
}
