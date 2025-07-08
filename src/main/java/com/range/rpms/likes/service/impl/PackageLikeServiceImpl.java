package com.range.rpms.likes.service.impl;

import com.range.rpms.common.util.UserContext;
import com.range.rpms.likes.domain.entity.PackageLike;
import com.range.rpms.likes.domain.repository.PackageLikeRepository;
import com.range.rpms.likes.exception.LikeNotFoundException;
import com.range.rpms.likes.exception.PackageAlreadyLikedException;
import com.range.rpms.likes.exception.PackageLikeAuthorException;
import com.range.rpms.likes.service.PackageLikeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PackageLikeServiceImpl implements PackageLikeService {
    private final UserContext userContext;
    private final PackageLikeRepository packageLikeRepository;
    public PackageLikeServiceImpl(PackageLikeRepository packageLikeRepository,UserContext userContext) {
        this.packageLikeRepository = packageLikeRepository;
        this.userContext = userContext;
    }


    @Override
    public void likePackage(Long id) {
        Long userId=getUserId();
        if (packageLikeRepository.existsByUserIdAndPackageId(userId,id)){
            throw new PackageAlreadyLikedException("Package already liked");
        }
        PackageLike packageLike=  PackageLike.builder().userId(userId).packageId(id).build();
        packageLikeRepository.save(packageLike);

    }

    @Override
    public void removeLikePackage(Long id) {
        Long userId=getUserId();
        PackageLike packageLike =packageLikeRepository.findById(id).orElseThrow(()->new LikeNotFoundException("Like Not Found"));
        if(!Objects.equals(packageLike.getUserId(), userId)){
            throw new   PackageLikeAuthorException("You can't remove like of other user");
        }
        packageLikeRepository.delete(packageLike);
    }

    @Override
    public Integer getPackageLikes(Long id) {
        List<PackageLike> packageLike =packageLikeRepository.findByPackageId(id);
        return packageLike.size();
    }



    private Long getUserId(){
        return userContext.getCurrentUserId();
    }
}
