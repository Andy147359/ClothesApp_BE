package com.dtk.ClothesApp.domain.response.CartItem;

import lombok.Data;

@Data
public class CartItemResponse {
    private String id;
    private CartUser user;
    private CartProduct product;
    private int quantity;

    @Data
    public static class CartUser {
        private String id;
        private String fullName;
        private String email;
    }

    @Data
    public static class CartProduct {
        private String id;
        private String name;
        private String price;
        private String discountPrice;
        private String description;
        private String stock;
        private String imageUrl;
    }

}
