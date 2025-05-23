package com.range.rpms.friend.service;

import com.range.rpms.friend.dto.FriendRequestDto;

import java.util.List;

public interface FriendRequestService {
    void sendFriendRequest(Long id);
    void cancelFriendRequest(Long id);
    List<FriendRequestDto> pendingFriendRequests();


}
