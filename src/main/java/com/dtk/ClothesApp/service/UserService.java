package com.dtk.ClothesApp.service;

import com.dtk.ClothesApp.domain.entity.User;
import com.dtk.ClothesApp.domain.request.CreateUserRequest;
import com.dtk.ClothesApp.domain.request.UpdateUserRequest;
import com.dtk.ClothesApp.domain.response.User.CreateUserResponse;
import com.dtk.ClothesApp.domain.response.User.UserResponse;

import jakarta.validation.Valid;
import java.util.List;

public interface UserService {
    // Create User
    CreateUserResponse createUser(@Valid CreateUserRequest user);

    // Get All Users
    List<UserResponse> getAllUsers();

    // Get User By ID
    UserResponse getUserById(String id);

    // Update User
    UserResponse updateUser(String id, @Valid UpdateUserRequest user);

    // Delete User
    void deleteUser(String id);
}
