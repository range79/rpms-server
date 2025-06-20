package com.range.rpms.friend.service.impl;

import com.range.rpms.friend.dao.model.FriendRequest;
import com.range.rpms.friend.dao.repository.FriendRequestRepository;
import com.range.rpms.friend.dto.FriendRequestDto;
import com.range.rpms.friend.enums.FriendRequestStatus;
import com.range.rpms.friend.exception.FriendRequestAlreadyExistsException;
import com.range.rpms.friend.mapper.FriendMapper;
import com.range.rpms.friend.service.FriendRequestService;
import com.range.rpms.friend.service.helper.FriendRequestServiceHelper;
import com.range.rpms.user.dao.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {
    private final FriendRequestRepository friendRequestRepository;
    private final FriendRequestServiceHelper helper;
    private final FriendMapper friendMapper;
    public FriendRequestServiceImpl(FriendRequestRepository friendRequestRepository,
                                    FriendRequestServiceHelper friendRequestServiceHelper,
                                    FriendMapper friendMapper) {
        this.friendRequestRepository = friendRequestRepository;
        this.helper = friendRequestServiceHelper;
        this.friendMapper = friendMapper;
    }



    @Override
    public void sendFriendRequest(Long id) {
        User sender = helper.getCurrentUser();
        User receiver = helper.getRecevierUserById(id);

        if (friendRequestRepository.existsBySenderAndReceiver(sender, receiver)) {
            throw new FriendRequestAlreadyExistsException("Friend request already sent");
        }
        FriendRequest request =  FriendRequest.builder().sender(sender)
                .receiver(receiver)
                .senderName(sender.getUsername())
                .receiverName(receiver.getUsername())
                .status(FriendRequestStatus.PENDING)
                .createTime(LocalDateTime.now())
                .build();

        friendRequestRepository.save(request);


    }

    @Override
    public void cancelFriendRequest(Long id) {
        User sender = helper.getCurrentUser();
        User receiver = helper.getRecevierUserById(id);

        if (!friendRequestRepository.existsBySenderAndReceiver(sender, receiver)){
            friendRequestRepository.deleteFriendRequestBySenderAndReceiver(sender, receiver);
        }

    }

    @Override
    public List<FriendRequestDto> pendingFriendRequests() {
        String currentUsername = helper.getCurrentUsername();
        return  friendRequestRepository
                .findBySender_Username(currentUsername)
                .stream()
                .map(friendMapper::friendRequestToFriendRequestDto)
                .collect(Collectors.toList());
    }

}
