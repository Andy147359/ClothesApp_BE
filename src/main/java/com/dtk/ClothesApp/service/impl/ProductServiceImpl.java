package com.dtk.ClothesApp.service.impl;

import com.dtk.ClothesApp.domain.entity.Product;
import com.dtk.ClothesApp.domain.mapper.ProductMapper;
import com.dtk.ClothesApp.domain.request.product.CreateProductRequest;
import com.dtk.ClothesApp.domain.request.product.UpdateProductRequest;
import com.dtk.ClothesApp.domain.response.Product.CreateProductResponse;
import com.dtk.ClothesApp.domain.response.Product.ProductResponse;
import com.dtk.ClothesApp.repository.ProductRepository;
import com.dtk.ClothesApp.service.ProductService;
import com.dtk.ClothesApp.util.exception.IdInvalidExceptionHandler;
import com.dtk.ClothesApp.util.exception.ResourceAlreadyExistsException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public CreateProductResponse createProduct(@Valid CreateProductRequest productRequest) {
        if (productRepository.findByName(productRequest.getName()).isPresent()) {
            throw new ResourceAlreadyExistsException("Product already exists with name: " + productRequest.getName());
        }

        Product product = productMapper.createProductRequestToProduct(productRequest);
        product.setDeleted(false);
        Product savedProduct = productRepository.save(product);
        return productMapper.productToCreateProductResponse(savedProduct);
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
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IdInvalidExceptionHandler("Product not found with id: " + id));
        product.setDeleted(true);
        productRepository.save(product);
    }
}
