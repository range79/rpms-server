package com.range.rpms.friend.domain.model;


import com.range.rpms.friend.enums.FriendRequestStatus;
import com.range.rpms.user.domain.model.User;
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

    private Long senderName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private Long receiverName;

    @Enumerated(EnumType.STRING)
    private FriendRequestStatus status;

    private LocalDateTime createTime;
}
