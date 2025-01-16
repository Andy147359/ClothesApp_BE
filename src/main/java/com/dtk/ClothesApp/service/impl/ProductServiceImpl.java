package com.dtk.ClothesApp.service.impl;

import com.dtk.ClothesApp.domain.entity.Product;
import com.dtk.ClothesApp.domain.mapper.ProductMapper;
import com.dtk.ClothesApp.domain.request.product.CreateProductRequest;
import com.dtk.ClothesApp.domain.request.product.UpdateProductRequest;
import com.dtk.ClothesApp.domain.response.Product.CreateProductResponse;
import com.dtk.ClothesApp.domain.response.Product.ProductResponse;
import com.dtk.ClothesApp.repository.ProductRepository;
import com.dtk.ClothesApp.service.CloudStorageService;
import com.dtk.ClothesApp.service.ProductService;
import com.dtk.ClothesApp.util.exception.IdInvalidExceptionHandler;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CloudStorageService cloudStorageService;

    @Override
    @Transactional
    public CreateProductResponse createProduct(
            String name, BigDecimal price, BigDecimal discountPrice,
            String description,
            Integer stock, String category, MultipartFile imageFile) {
        // Upload ảnh lên Cloudinary
        String imageUrl = cloudStorageService.uploadFile(imageFile);

        // Tạo Product
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDiscountPrice(discountPrice);
        product.setDescription(description);
        product.setStock(stock);
        product.setCategory(category);
        product.setImageUrl(imageUrl);

        // Lưu vào DB
        Product savedProduct = productRepository.save(product);

        // Trả về response
        return new CreateProductResponse(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getPrice(),
                savedProduct.getDiscountPrice(),
                savedProduct.getDescription(),
                savedProduct.getStock(),
                savedProduct.getCategory(),
                savedProduct.getImageUrl());
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .filter(product -> !product.isDeleted())
                .map(productMapper::productToProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id)
                .filter(p -> !p.isDeleted())
                .orElseThrow(() -> new IdInvalidExceptionHandler("Product not found with id: " + id));
        return productMapper.productToProductResponse(product);
    }

    @Override
    public ProductResponse getProductByName(String name) {
        Product product = productRepository.findByName(name)
                .filter(p -> !p.isDeleted())
                .orElseThrow(() -> new IdInvalidExceptionHandler("Product not found with name: " + name));
        return productMapper.productToProductResponse(product);
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(String id, @Valid UpdateProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .filter(p -> !p.isDeleted())
                .orElseThrow(() -> new IdInvalidExceptionHandler("Product not found with id: " + id));

        // Update fields
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setDiscountPrice(productRequest.getDiscountPrice());
        product.setDescription(productRequest.getDescription());
        product.setStock(productRequest.getStock());
        product.setImageUrl(productRequest.getImageUrl());

        Product updatedProduct = productRepository.save(product);
        return productMapper.productToProductResponse(updatedProduct);
    }

    @Override
    @Transactional
    public ProductResponse updateProductStock(String id, int stock) {
        Product product = productRepository.findById(id)
                .filter(p -> !p.isDeleted())
                .orElseThrow(() -> new IdInvalidExceptionHandler("Product not found with id: " + id));
        product.setStock(stock);
        Product updatedProduct = productRepository.save(product);
        return productMapper.productToProductResponse(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IdInvalidExceptionHandler("Product not found with id: " + id));
        product.setDeleted(true);
        productRepository.save(product);
    }
}
