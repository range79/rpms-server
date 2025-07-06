package com.range.rpms.friend.mapper;

import com.range.rpms.friend.domain.model.FriendRequest;
import com.range.rpms.friend.dto.FriendRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FriendMapper {

    @Mapping(source = "sender.username",target = "senderName")
    @Mapping(source = "receiver.username",target = "receiverName")
    FriendRequestDto friendRequestToFriendRequestDto(FriendRequest friendRequest);
}
