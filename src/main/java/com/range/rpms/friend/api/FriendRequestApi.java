package com.range.rpms.friend.api;

import com.range.rpms.common.dto.GenericResponse;
import com.range.rpms.friend.dto.FriendRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/${app.base-path}/friends/")
public interface FriendRequestApi {
    @Operation(
            summary = "send friend request",
            description = "sends to user with id a friend request"
    )
    @PostMapping("/add/{id}")
    ResponseEntity<GenericResponse<Void>> sendFriendRequest(@PathVariable Long id);



    @Operation(
            summary = "cancel friend request",
            description = "delete the friend request"
    )
    @DeleteMapping("/cancel/{id}")
    ResponseEntity<GenericResponse<Void>> cancelFriendRequest(@PathVariable Long id);


    @Operation(
            summary = "get all friend requests",
            description = "gets all friend request come to user"
    )
    @GetMapping("/pending")
    ResponseEntity<GenericResponse<List<FriendRequestDto>>> getPendingFriendRequests();

}
