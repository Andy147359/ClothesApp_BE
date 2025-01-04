package com.dtk.ClothesApp.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "djnavkxkp");
        config.put("api_key", "352571553529866");
        config.put("api_secret", "ziCOweK8kn64JmsKi1oNQcdcYdg");
        return new Cloudinary(config);
    }
}
