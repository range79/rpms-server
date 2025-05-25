package com.range.rpms.friend.controller;

import com.range.rpms.common.dto.GenericResponse;
import com.range.rpms.friend.api.FriendRequestApi;
import com.range.rpms.friend.dto.FriendRequestDto;
import com.range.rpms.friend.service.FriendRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FriendRequestController implements FriendRequestApi {
    private final FriendRequestService friendRequestService;
    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;


    }
    @Override
    public ResponseEntity<GenericResponse<Void>> sendFriendRequest(Long id) {
        friendRequestService.sendFriendRequest(id);
        return ResponseEntity.ok(new GenericResponse<>(
                        true,
                        "Friend request to user with ID " + id + " has been successfully canceled.",
                        HttpStatus.OK.value(),
                        null
                )
        );
    }

    @Override
    public ResponseEntity<GenericResponse<Void>> cancelFriendRequest(Long id) {
        friendRequestService.cancelFriendRequest(id);
        return ResponseEntity.ok(new GenericResponse<>(
                true,
                "Friend request successfully sent to user with ID: " + id,
                HttpStatus.OK.value(),
                null
        ));
    }

    @Override
    public ResponseEntity<GenericResponse<List<FriendRequestDto>>> getPendingFriendRequests() {
        List<FriendRequestDto> pendingRequests = friendRequestService.pendingFriendRequests();
        return ResponseEntity.ok(new GenericResponse<>(
                        true,
                        "Pending friend requests fetched successfully.",
                        HttpStatus.OK.value(),
                        pendingRequests
                )
        );
    }
}
