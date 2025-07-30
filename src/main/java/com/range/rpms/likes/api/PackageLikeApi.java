package com.range.rpms.likes.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/${app.base-path}/likes/package")
public interface PackageLikeApi {
    @PostMapping("{packageId}")
    ResponseEntity<Void> likePackage(Long id);
    @DeleteMapping("{packageId}")
    ResponseEntity<Void> removeLikePackage(Long id);
    @PostMapping("{packageId}/count")
    ResponseEntity<Integer> getPackageLikes(Long id);


}
