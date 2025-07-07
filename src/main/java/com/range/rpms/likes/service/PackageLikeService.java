package com.range.rpms.likes.service;

import com.range.rpms.likes.domain.entity.PackageLike;

import java.util.List;

public interface PackageLikeService {

    void likePackage(Long id);
    void dislikePackage(Long id);
    List<PackageLike> getPackageLikes(Long id);


}
