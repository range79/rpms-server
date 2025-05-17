package com.range.rpms.friend.dao.repository;

import com.range.rpms.friend.dao.model.FriendRequest;
import com.range.rpms.user.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    boolean existsBySenderAndReceiver(User sender, User receiver);
}
