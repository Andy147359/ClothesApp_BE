package com.dtk.ClothesApp.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Password không được để trống")
    @Size(min = 6, max = 20, message = "Password phải từ 6 đến 20 ký tự")
    private String password;

    @NotBlank(message = "Full name không được để trống")
    @Size(max = 50, message = "Full name không được vượt quá 50 ký tự")
    private String fullName;
}
