package com.dtk.ClothesApp.service.impl;

import com.dtk.ClothesApp.domain.entity.User;
import com.dtk.ClothesApp.domain.mapper.UserMapper;
import com.dtk.ClothesApp.domain.request.User.CreateUserRequest;
import com.dtk.ClothesApp.domain.request.User.UpdateUserRequest;
import com.dtk.ClothesApp.domain.response.User.CreateUserResponse;
import com.dtk.ClothesApp.domain.response.User.UserResponse;
import com.dtk.ClothesApp.repository.UserRepository;
import com.dtk.ClothesApp.service.UserService;
import com.dtk.ClothesApp.util.exception.IdInvalidExceptionHandler;
import com.dtk.ClothesApp.util.exception.ResourceAlreadyExistsException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    // Create User
    @Override
    public CreateUserResponse createUser(@Valid CreateUserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists: " + userRequest.getEmail());
        }

        User user = userMapper.createUserRequestToUser(userRequest);
        user.setRole("BUYER");
        user.setDeleted(false);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        User savedUser = userRepository.save(user);
        return userMapper.userToCreateUserResponse(savedUser);
    }

    // Get All Users
    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .filter(user -> !user.isDeleted())
                .map(userMapper::userToUserResponse)
                .collect(Collectors.toList());
    }

    // Get User By ID
    @Override
    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id)
                .filter(u -> !u.isDeleted())
                .orElseThrow(() -> new IdInvalidExceptionHandler("User not found with id: " + id));
        return userMapper.userToUserResponse(user);
    }

    // Get User By Email
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IdInvalidExceptionHandler("User not found with email: " + email));
    }

    // Update User
    @Override
    public UserResponse updateUser(String id, @Valid UpdateUserRequest updatedUserData) {
        User user = userRepository.findById(id)
                .filter(u -> !u.isDeleted())
                .orElseThrow(() -> new IdInvalidExceptionHandler("User not found with id: " + id));

        // Cập nhật các trường
        user.setEmail(updatedUserData.getEmail());
        user.setFullName(updatedUserData.getFullName());
        user.setRole(updatedUserData.getRole());
        user.setPassword(passwordEncoder.encode(updatedUserData.getPassword()));

        User updatedUser = userRepository.save(user);
        return userMapper.userToUserResponse(updatedUser);
    }

    // Delete User
    @Override
    public void deleteUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IdInvalidExceptionHandler("User not found with id: " + id));

        user.setDeleted(true);
        userRepository.save(user);
    }
}
