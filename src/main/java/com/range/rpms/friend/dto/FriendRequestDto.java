package com.range.rpms.friend.dto;

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
