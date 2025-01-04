package com.dtk.ClothesApp.domain.request.Auth;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String email;
    private String newPassword;
}
