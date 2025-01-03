package com.dtk.ClothesApp.domain.request.product;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class UpdateProductRequest {
    private String name;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private String description;
    private int stock;
    private String imageUrl;
}