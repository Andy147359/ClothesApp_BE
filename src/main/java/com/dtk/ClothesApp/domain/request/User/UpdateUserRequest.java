package com.dtk.ClothesApp.domain.request.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Password không được để trống")
    private String password;

    @NotBlank(message = "Full name không được để trống")
    private String fullName;

    @NotBlank(message = "Role không được để trống")
    private String role;
}
