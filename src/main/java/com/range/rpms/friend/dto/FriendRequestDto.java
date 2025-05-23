package com.range.rpms.friend.dto;

import com.range.rpms.friend.enums.FriendRequestStatus;
import com.range.rpms.user.dao.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class FriendRequestDto {

    private long id;
    @NotNull
    private String senderName;
    @NotNull
    private String receiverName;

    private LocalDateTime createTime;
}
