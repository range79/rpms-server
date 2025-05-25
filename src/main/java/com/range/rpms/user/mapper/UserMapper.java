package com.range.rpms.user.mapper;

import com.range.rpms.user.dao.model.User;
import com.range.rpms.user.dto.UserRegisterRequest;
import com.range.rpms.user.dto.UserRegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
 User userRegisterRequestToUser(UserRegisterRequest userRegisterRequest);


 UserRegisterResponse userToUserRegisterResponse(User user);

}
