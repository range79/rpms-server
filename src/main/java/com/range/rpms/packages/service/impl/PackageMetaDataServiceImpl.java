package com.range.rpms.packages.service.impl;

import com.range.rpms.friend.dao.model.Friend;
import com.range.rpms.friend.dao.repository.FriendRepository;
import com.range.rpms.packages.dao.repository.PackageRepository;
import com.range.rpms.packages.dto.PackageMetaData;
import com.range.rpms.packages.enums.PackageVisibility;
import com.range.rpms.packages.exception.PackageNotFoundException;
import com.range.rpms.packages.mapper.PackageMapper;
import com.range.rpms.packages.service.PackageMetaDataService;
import com.range.rpms.common.util.UserContextUtil;
import com.range.rpms.user.dao.model.User;
import com.range.rpms.user.dao.repository.UserRepository;
import com.range.rpms.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PackageMetaDataServiceImpl implements PackageMetaDataService {

    private final PackageRepository packagerepository;

    private final PackageMapper packageMapper;
    private final UserContextUtil springSecurityUserContextUtil;
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    public PackageMetaDataServiceImpl(PackageRepository packagerepository,
                                      PackageMapper packageMapper,
                                      UserContextUtil springSecurityUserContextUtil,
                                      FriendRepository friendRepository,
                                      UserRepository userRepository) {
        this.springSecurityUserContextUtil = springSecurityUserContextUtil;
        this.packagerepository = packagerepository;
        this.packageMapper = packageMapper;
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
    }
    /**
     * This service is for server administrators.
     * Instead of opening Cassandra and manually querying the database,
     * administrators can use the /search/all endpoint to list packages.
     */
    @Override
    public List<PackageMetaData> getAllPackages() {
      {
            String username = springSecurityUserContextUtil.getCurrentUserName();
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException("User Not Found"));

            List<Friend> userFriends = friendRepository.findFriendBySenderOrReceiver(user, user);

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
                                    pkg.getAuthor().equals(friend.getSender().getUsername()) ||
                                            pkg.getAuthor().equals(friend.getReceiver().getUsername())
                            ))
                    .map(packageMapper::toPackageMetaData)
                    .toList();


            publicPackages.addAll(friendPackages);
            return publicPackages;
        }



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
        //if metadataList is empty throw an exception
        if (metaDataList.isEmpty()){

            throw new PackageNotFoundException("No package found with name: " +packageName);

        }return metaDataList;
    }


}
