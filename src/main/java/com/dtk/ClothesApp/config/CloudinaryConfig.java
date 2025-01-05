package com.dtk.ClothesApp.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "djnavkxkp",
                "api_key", "352571553529866",
                "api_secret", "ziCOweK8kn64JmsKi1oNQcdcYdg"));
    }
}
