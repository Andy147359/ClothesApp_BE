package com.dtk.ClothesApp.domain.response.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String userId;
    private String email;
    private String fullName;
    private String role;
    private String message;
}