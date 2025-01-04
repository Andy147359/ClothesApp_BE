package com.dtk.ClothesApp.domain.response.Order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderResponse {
    private String id;
    private BigDecimal totalAmount;
    private BigDecimal shippingCost;
    private String status;
    private String address;
    private UserResponse user;
    private List<OrderItemResponse> orderItems;

    @Data
    public static class UserResponse {
        private String id;
        private String fullName;
        private String email;
    }

    @Data
    public static class OrderItemResponse {
        private String id;
        private ProductResponse product;
        private int quantity;

        @Data
        public static class ProductResponse {
            private String id;
            private String name;
            private BigDecimal price;
        }
    }
}
