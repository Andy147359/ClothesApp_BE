package com.dtk.ClothesApp.domain.request.Order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateOrderRequest {
    @NotNull(message = "Total amount is required")
    private BigDecimal totalAmount;

    @NotNull(message = "Shipping cost is required")
    private BigDecimal shippingCost;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotNull(message = "Order items are required")
    private List<CreateOrderItemRequest> orderItems;

    @Data
    public static class CreateOrderItemRequest {
        @NotBlank(message = "Product ID is required")
        private String productId;

        @NotNull(message = "Quantity is required")
        private int quantity;
    }
}
