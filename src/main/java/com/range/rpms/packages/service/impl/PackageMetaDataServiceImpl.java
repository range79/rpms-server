package com.range.rpms.packages.service.impl;

import com.range.rpms.common.util.UserContext;
import com.range.rpms.friend.domain.model.Friend;
import com.range.rpms.friend.service.FriendService;
import com.range.rpms.packages.domain.repository.PackageRepository;
import com.range.rpms.packages.dto.PackageMetaData;
import com.range.rpms.packages.enums.PackageVisibility;
import com.range.rpms.packages.exception.PackageNotFoundException;
import com.range.rpms.packages.mapper.PackageMapper;
import com.range.rpms.packages.service.PackageMetaDataService;
import com.range.rpms.user.domain.model.User;
import com.range.rpms.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PackageMetaDataServiceImpl implements PackageMetaDataService {

    private final PackageRepository packagerepository;

    private final PackageMapper packageMapper;
    private final UserContext userContext;
    private final FriendService friendService;
    private final UserService userService;

    public PackageMetaDataServiceImpl(PackageRepository packagerepository,
                                      PackageMapper packageMapper,
                                      UserContext userContext,
                                      FriendService friendService,
                                      UserService userService) {
        this.userContext = userContext;
        this.packagerepository = packagerepository;
        this.packageMapper = packageMapper;
        this.userService = userService;
        this.friendService = friendService;
    }
    /**
     * This service is for server administrators.
     * Instead of opening Cassandra and manually querying the database,
     * administrators can use the /search/all endpoint to list packages.
     */
    @Override
    public List<PackageMetaData> getAllPackages() {


        User user = userService.findUser(userContext.getCurrentUserId());

        List<Friend> userFriends = friendService.getFriends(user);

        List<PackageMetaData> publicPackages = packagerepository
                .findAllByPackageVisibility(PackageVisibility.PUBLIC)
                .stream()
                .map(packageMapper::toPackageMetaData)
                .collect(Collectors.toList());


        List<PackageMetaData> friendPackages = packagerepository
                .findAllByPackageVisibility(PackageVisibility.ONLY_FRIEND)
                .stream()
                .filter(pkg -> userFriends.stream()
                        .anyMatch(friend ->
                                pkg.getAuthor().equals(friend.getSender().getId()) ||
                                        pkg.getAuthor().equals(friend.getReceiver().getId())
                        ))
                .map(packageMapper::toPackageMetaData)
                .toList();


        publicPackages.addAll(friendPackages);
        return publicPackages;
    }




    /**
     * Searches for a package in the database by name.
     * This service is accessed via the search/{packageName} endpoint.
     * Usage (on the Range package manager client): rpmc search {package_name}
     */
    @Override
    public List<PackageMetaData> searchPackage(String packageName) throws PackageNotFoundException {

        List<PackageMetaData> metaDataList=   packagerepository
                .findByNameLike(packageName)
                .stream()
                /*
                 * Use DTO to avoid sending full package content.
                 * Returning all package data including file content could lead to memory overload.
                 */
                .map(packageMapper::toPackageMetaData)

                .collect(Collectors.toList());

        if (metaDataList.isEmpty()){

            throw new PackageNotFoundException("No package found with name: " +packageName);

        }
        return metaDataList;
    }


}
