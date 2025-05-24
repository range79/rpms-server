package com.range.rpms.friend.controller;

import com.range.rpms.common.dto.GenericResponse;
import com.range.rpms.friend.api.FriendRequestActionApi;
import com.range.rpms.friend.service.FriendRequestActionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendRequestActionController implements FriendRequestActionApi {
    private final FriendRequestActionService friendRequestActionService;
    public FriendRequestActionController(FriendRequestActionService friendRequestActionService) {
        this.friendRequestActionService = friendRequestActionService;
    }
    @Override
    public ResponseEntity<GenericResponse<Void>> acceptFriendRequest(Long id) {
        friendRequestActionService.acceptFriendRequest(id);

        return ResponseEntity.ok(new GenericResponse<>(
                true,
                "Friend request accepted successfully",
                HttpStatus.OK.value(),
                null
        ));

    }

    @Override
    public ResponseEntity<GenericResponse<Void>> removeFriendRequest(Long id) {

        friendRequestActionService.removeFriend(id);

        return ResponseEntity.ok(new GenericResponse<>(
                true,
                "Friend request removed successfully",
                HttpStatus.OK.value(),
                null
        ));


    }

    @Override
    public ResponseEntity<GenericResponse<Void>> rejectFriendRequest(Long id) {
        friendRequestActionService.rejectFriendRequest(id);
        return ResponseEntity.ok(new GenericResponse<>(
                true,
                "Friend request accepted successfully",
                HttpStatus.OK.value(),
                null
        ));
    }
}
