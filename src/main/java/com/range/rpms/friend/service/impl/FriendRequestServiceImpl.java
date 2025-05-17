package com.range.rpms.friend.service.impl;

import com.range.rpms.friend.dao.model.FriendRequest;
import com.range.rpms.user.dao.model.User;
import com.range.rpms.friend.dao.repository.FriendRequestRepository;
import com.range.rpms.user.dao.repository.UserRepository;
import com.range.rpms.friend.enums.FriendRequestStatus;
import com.range.rpms.friend.exception.FriendRequestAlreadyExistsException;
import com.range.rpms.friend.service.FriendRequestService;
import com.range.rpms.common.util.SpringSecurityUserContextUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {
    private FriendRequestRepository friendRequestRepository;
    private SpringSecurityUserContextUtil springSecurityUserContextUtil;
    private UserRepository userRepository;
    public FriendRequestServiceImpl(FriendRequestRepository friendRequestRepository
            , SpringSecurityUserContextUtil springSecurityUserContextUtil,
                                    UserRepository userRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
        this.springSecurityUserContextUtil = springSecurityUserContextUtil;
    }



    @Override
    public void addFriendRequest(Long id) {

        String senderUsername = springSecurityUserContextUtil.getCurrentUserName();
        User sender = userRepository.findByUsername(senderUsername)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        User receiver = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));
        if (friendRequestRepository.existsBySenderAndReceiver(sender, receiver)) {
            throw new FriendRequestAlreadyExistsException("Friend request already sent");
        }
        FriendRequest request = new FriendRequest().builder().sender(sender)
                .receiver(receiver)
                .senderName(sender.getUsername())
                .receiverName(receiver.getUsername())
                .status(FriendRequestStatus.PENDING)
                .createTime(LocalDateTime.now())
                .build();

        friendRequestRepository.save(request);


    }

    @Override
    public void deleteFriendRequest(Long id) {
        String currentUsername=springSecurityUserContextUtil.getCurrentUserName();
        String senderUsername = springSecurityUserContextUtil.getCurrentUserName();
        User sender = userRepository.findByUsername(senderUsername)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        User receiver = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));
        if (friendRequestRepository.existsBySenderAndReceiver(sender, receiver)){

        }

    }

}
