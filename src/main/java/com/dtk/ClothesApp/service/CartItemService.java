package com.dtk.ClothesApp.service;

import java.util.List;
import java.util.Optional;

import com.dtk.ClothesApp.domain.entity.CartItem;
import com.dtk.ClothesApp.domain.request.CartItem.CreateCartItemRequest;
import com.dtk.ClothesApp.domain.request.CartItem.UpdateCartItemRequest;
import com.dtk.ClothesApp.domain.response.CartItem.CartItemResponse;

public interface CartItemService {
    CartItemResponse addToCart(CreateCartItemRequest request);

    List<CartItemResponse> getCartItemsByUser(String userId);

    CartItemResponse updateCartItemQuantity(String id, UpdateCartItemRequest request);

    CartItemResponse updateQuantityByUserAndProductId(CreateCartItemRequest request);

    void removeFromCart(String id);

    Optional<CartItem> productExistInUserCart(String userId, String productId);
}
