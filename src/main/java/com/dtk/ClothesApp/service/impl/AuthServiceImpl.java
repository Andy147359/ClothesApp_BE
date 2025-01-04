package com.dtk.ClothesApp.service.impl;

import com.dtk.ClothesApp.domain.entity.User;
import com.dtk.ClothesApp.domain.request.User.CreateUserRequest;
import com.dtk.ClothesApp.domain.request.auth.LoginRequest;
import com.dtk.ClothesApp.domain.request.auth.ResetPasswordRequest;
import com.dtk.ClothesApp.domain.response.Auth.AuthResponse;
import com.dtk.ClothesApp.domain.response.User.CreateUserResponse;
import com.dtk.ClothesApp.repository.UserRepository;
import com.dtk.ClothesApp.service.AuthService;
import com.dtk.ClothesApp.service.UserService;
import com.dtk.ClothesApp.util.exception.IdInvalidExceptionHandler;
import com.dtk.ClothesApp.util.exception.ResourceAlreadyExistsException;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userService.getUserByEmail(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IdInvalidExceptionHandler("Invalid email or password");
        }

        return new AuthResponse(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getRole(),
                "Login successful");
    }

    @Override
    public AuthResponse register(CreateUserRequest request) {
        CreateUserResponse newUser = userService.createUser(request);

        return new AuthResponse(
                newUser.getId(),
                newUser.getEmail(),
                newUser.getFullName(),
                newUser.getRole(),
                "Registration successful");
    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IdInvalidExceptionHandler("User not found"));

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}
