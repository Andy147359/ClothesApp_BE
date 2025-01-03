package com.dtk.ClothesApp.service;

import com.dtk.ClothesApp.domain.request.product.CreateProductRequest;
import com.dtk.ClothesApp.domain.request.product.UpdateProductRequest;
import com.dtk.ClothesApp.domain.response.Product.CreateProductResponse;
import com.dtk.ClothesApp.domain.response.Product.ProductResponse;

import jakarta.validation.Valid;
import java.util.List;

public interface ProductService {
    CreateProductResponse createProduct(@Valid CreateProductRequest product);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(String id);

    ProductResponse getProductByName(String name);

    ProductResponse updateProduct(String id, @Valid UpdateProductRequest product);

    void deleteProduct(String id);
}
