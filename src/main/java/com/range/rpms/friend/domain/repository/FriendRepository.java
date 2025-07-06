package com.range.rpms.friend.domain.repository;

import com.range.rpms.friend.domain.model.Friend;
import com.range.rpms.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    Friend deleteFriendBySenderAndReceiver(User sender, User receiver);

    void deleteFriendBySenderAndReceiverOrSenderAndReceiver(User sender, User receiver, User sender1, User receiver1);

    List<Friend> findFriendBySenderOrReceiver(User sender, User receiver);
}
