package com.range.rpms.mapper;

import com.range.rpms.dao.model.User;
import com.range.rpms.dto.user.UserRegisterRequest;
import com.range.rpms.dto.user.UserRegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
 User userRegisterRequestToUser(UserRegisterRequest userRegisterRequest);


 UserRegisterResponse userToUserRegisterResponseToUser(User user);

}
