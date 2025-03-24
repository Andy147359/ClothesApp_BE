package com.dtk.ClothesApp.domain.mapper;

import com.dtk.ClothesApp.domain.entity.Product;
import com.dtk.ClothesApp.domain.request.product.CreateProductRequest;
import com.dtk.ClothesApp.domain.response.Product.CreateProductResponse;
import com.dtk.ClothesApp.domain.response.Product.ProductResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product createProductRequestToProduct(CreateProductRequest request);

    @Mapping(source = "category", target = "category")
    ProductResponse productToProductResponse(Product product);

    CreateProductResponse productToCreateProductResponse(Product product);
}
