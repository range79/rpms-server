package com.range.rpms.friend.api;

import com.range.rpms.common.dto.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/friends")
public interface FriendRequestActionApi {

    @PostMapping("/accept/{id}")
    ResponseEntity<GenericResponse<Void>> acceptFriendRequest(@PathVariable Long id);
    @DeleteMapping("/remove/{id}")
    ResponseEntity<GenericResponse<Void>> removeFriendRequest(@PathVariable Long id);
    @DeleteMapping("/reject/{id}")
    ResponseEntity<GenericResponse<Void>> rejectFriendRequest(@PathVariable Long id);







}
