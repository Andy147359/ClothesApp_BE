package com.dtk.ClothesApp.service;

import com.dtk.ClothesApp.domain.response.Product.CreateProductResponse;
import com.dtk.ClothesApp.domain.response.Product.ProductResponse;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    CreateProductResponse createProduct(
            String name, BigDecimal price, BigDecimal discountPrice, String description,
            Integer stock, String category, MultipartFile imageFile);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(String id);

    ProductResponse getProductByName(String name);

    ProductResponse updateProduct(
            String id,
            String name, BigDecimal price, BigDecimal discountPrice, String description,
            Integer stock, String category, MultipartFile imageFile);

    void updateProductStock(String id, int stock);

    void deleteProduct(String id);
}
