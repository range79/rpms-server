package com.range.rpms.users.mapper;

import com.range.rpms.users.domain.model.User;
import com.range.rpms.users.dto.UserRegisterRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
 User userRegisterRequestToUser(UserRegisterRequest userRegisterRequest);




}
