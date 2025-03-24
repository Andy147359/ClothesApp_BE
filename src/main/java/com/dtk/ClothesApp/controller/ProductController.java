package com.dtk.ClothesApp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.dtk.ClothesApp.domain.request.product.CreateProductRequest;
import com.dtk.ClothesApp.domain.request.product.UpdateProductRequest;
import com.dtk.ClothesApp.domain.response.Product.CreateProductResponse;
import com.dtk.ClothesApp.domain.response.Product.ProductResponse;
import com.dtk.ClothesApp.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    // Create Product
    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(
            @RequestParam String name,
            @RequestParam BigDecimal price,
            @RequestParam(required = false) BigDecimal discountPrice,
            @RequestParam(required = false) String description,
            @RequestParam Integer stock,
            @RequestParam String category,
            @RequestParam MultipartFile imageFile) {
        CreateProductResponse response = productService.createProduct(
                name, price, discountPrice, description, stock, category, imageFile);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get All Products
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Get Product By ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // Update Product
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable String id,
            @RequestParam String name,
            @RequestParam BigDecimal price,
            @RequestParam(required = false) BigDecimal discountPrice,
            @RequestParam(required = false) String description,
            @RequestParam Integer stock,
            @RequestParam String category,
            @RequestParam(required = false) MultipartFile imageFile) {
        return ResponseEntity.ok(productService.updateProduct(
                id, name, price, discountPrice, description, stock, category, imageFile
        ));
    }

    // Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
