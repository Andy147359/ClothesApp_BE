package com.dtk.ClothesApp.domain.mapper;

import org.mapstruct.Mapper;

import com.dtk.ClothesApp.domain.entity.User;
import com.dtk.ClothesApp.domain.request.User.CreateUserRequest;
import com.dtk.ClothesApp.domain.response.User.CreateUserResponse;
import com.dtk.ClothesApp.domain.response.User.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User createUserRequestToUser(CreateUserRequest userRequest);

    CreateUserResponse userToCreateUserResponse(User user);

    UserResponse userToUserResponse(User user);

}
