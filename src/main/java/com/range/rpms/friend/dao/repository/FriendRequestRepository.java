package com.range.rpms.friend.dao.repository;

import com.range.rpms.friend.dao.model.FriendRequest;
import com.range.rpms.friend.dto.FriendRequestDto;
import com.range.rpms.user.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    boolean existsBySenderAndReceiver(User sender, User receiver);

    void deleteFriendRequestBySenderAndReceiver(User sender, User receiver);

    FriendRequestDto findBySender(User sender);

    List<FriendRequest> findBySender_Username(String senderUsername);
}
