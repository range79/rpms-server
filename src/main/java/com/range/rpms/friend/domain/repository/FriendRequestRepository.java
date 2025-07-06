package com.range.rpms.friend.domain.repository;

import com.range.rpms.friend.domain.model.FriendRequest;
import com.range.rpms.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    boolean existsBySenderAndReceiver(User sender, User receiver);

    void deleteFriendRequestBySenderAndReceiver(User sender, User receiver);


    List<FriendRequest> findBySender_Id(long id);
}
