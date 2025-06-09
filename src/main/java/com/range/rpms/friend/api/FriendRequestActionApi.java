package com.range.rpms.friend.api;

import com.range.rpms.common.dto.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/friends")
public interface FriendRequestActionApi {
    @Operation(
            summary = "accept friend",
            description = "accept friend request with id"
    )
    @PostMapping("/accept/{id}")
    ResponseEntity<GenericResponse<Void>> acceptFriendRequest(@PathVariable Long id);
    @Operation(
            summary = "remove friend",
            description = "remove friend request with id"
    )
    @DeleteMapping("/remove/{id}")
    ResponseEntity<GenericResponse<Void>> removeFriendRequest(@PathVariable Long id);
    @Operation(
            summary = "reject friend",
            description = "reject who do you hate"
    )
    @DeleteMapping("/reject/{id}")
    ResponseEntity<GenericResponse<Void>> rejectFriendRequest(@PathVariable Long id);







}
