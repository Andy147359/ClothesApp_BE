package com.dtk.ClothesApp.domain.response.Product;

import lombok.Data;

@Data
public class CreateProductResponse {
    private String id;
    private String name;
    private String price;
    private String discountPrice;
    private String description;
    private int stock;
    private String imageUrl;
}