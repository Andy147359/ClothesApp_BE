package com.dtk.ClothesApp.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudStorageService {
    // Upload file và trả về URL
    String uploadFile(MultipartFile file);

}
