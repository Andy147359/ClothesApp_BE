package com.dtk.ClothesApp.domain.response.Product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    private String id;
    private String name;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private String description;
    private int stock;
    private String category;
    private String imageUrl;
}