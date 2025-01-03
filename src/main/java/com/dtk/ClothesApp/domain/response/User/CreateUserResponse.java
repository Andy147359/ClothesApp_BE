package com.dtk.ClothesApp.domain.response.User;

import lombok.Data;

@Data
public class CreateUserResponse {
    private String id;
    private String email;
    private String fullName;
    private String role;
}
