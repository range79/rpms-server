package com.range.rpms.friend.dao.model;


import com.range.rpms.friend.enums.FriendRequestStatus;
import com.range.rpms.user.dao.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "friend_req")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    private String senderName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private String receiverName;

    @Enumerated(EnumType.STRING)
    private FriendRequestStatus status;

    private LocalDateTime createTime;
}
