package com.dtk.ClothesApp.domain.response.Product;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateProductResponse {
    private String id;
    private String name;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private String description;
    private Integer stock;
    private String imageUrl;
}