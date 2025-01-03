package com.dtk.ClothesApp.domain.request.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    private BigDecimal price;

    private BigDecimal discountPrice;
    private String description;

    @NotNull(message = "Stock is required")
    private int stock;

    private String imageUrl;
}