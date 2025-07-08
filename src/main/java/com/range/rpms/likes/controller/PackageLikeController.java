package com.range.rpms.likes.controller;

import com.range.rpms.likes.api.PackageLikeApi;
import com.range.rpms.likes.service.PackageLikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PackageLikeController implements PackageLikeApi {


    private PackageLikeService packageLikeService;
    public PackageLikeController(PackageLikeService packageLikeService) {
        this.packageLikeService = packageLikeService;
    }


    @Override
    public ResponseEntity<Void> likePackage(Long id) {
        packageLikeService.likePackage(id);
        return  ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<Void> removeLikePackage(Long id) {
        packageLikeService.removeLikePackage(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Integer> getPackageLikes(Long id) {
        return ResponseEntity.ok(packageLikeService.getPackageLikes(id));
    }
}
