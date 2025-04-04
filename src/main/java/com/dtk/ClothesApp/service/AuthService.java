package com.dtk.ClothesApp.service;

import com.dtk.ClothesApp.domain.request.Auth.LoginRequest;
import com.dtk.ClothesApp.domain.request.Auth.ResetPasswordRequest;
import com.dtk.ClothesApp.domain.request.User.CreateUserRequest;
import com.dtk.ClothesApp.domain.response.Auth.AuthResponse;

public interface AuthService {
    // User Login
    AuthResponse login(LoginRequest request);

    // User Registration
    AuthResponse register(CreateUserRequest request);

    // Reset Password
    void resetPassword(ResetPasswordRequest request);
}
