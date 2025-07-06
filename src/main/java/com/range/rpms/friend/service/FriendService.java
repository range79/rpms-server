package com.range.rpms.friend.service;

import com.range.rpms.friend.domain.model.Friend;
import com.range.rpms.friend.domain.model.FriendRequest;
import com.range.rpms.user.domain.model.User;

import java.util.List;

public interface FriendService  {
    List<Friend> getFriends (User user);
}
