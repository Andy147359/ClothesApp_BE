package com.dtk.ClothesApp.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dtk.ClothesApp.service.CloudStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudStorageService {

    private final Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            // Thêm tham số "folder" vào cấu hình upload
            Map<String, Object> uploadParams = ObjectUtils.asMap(
                    "folder", "ClothesApp");

            // Thực hiện upload
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);

            return uploadResult.get("secure_url").toString();
        } catch (IOException e) {
            System.err.println("Failed to upload file to Cloudinary: " + e.getMessage());
            throw new RuntimeException("Failed to upload file to Cloudinary", e);
        }
    }

}
