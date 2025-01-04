package com.dtk.ClothesApp.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.dtk.ClothesApp.domain.request.CartItem.CreateCartItemRequest;
import com.dtk.ClothesApp.domain.request.CartItem.UpdateCartItemRequest;
import com.dtk.ClothesApp.domain.response.CartItem.CartItemResponse;
import com.dtk.ClothesApp.service.CartItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart-items")
public class CartItemController {
    private final CartItemService cartItemService;

    // Add Item to Cart
    @PostMapping
    public ResponseEntity<CartItemResponse> addToCart(@Valid @RequestBody CreateCartItemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemService.addToCart(request));
    }

    // Get All Items in Cart for a User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartItemResponse>> getCartItemsByUser(@PathVariable String userId) {
        return ResponseEntity.ok(cartItemService.getCartItemsByUser(userId));
    }

    // Update Quantity of a Cart Item
    @PutMapping("/{id}")
    public ResponseEntity<CartItemResponse> updateQuantity(
            @PathVariable("id") String cartItemId,
            @RequestBody UpdateCartItemRequest quantity) {
        return ResponseEntity.ok(cartItemService.updateCartItemQuantity(cartItemId, quantity));
    }

    @PutMapping
    public ResponseEntity<CartItemResponse> updateQuantityByUserAndProductId(
            @RequestBody CreateCartItemRequest request) {
        return ResponseEntity.ok(cartItemService.updateQuantityByUserAndProductId(request));
    }

    // Remove Item from Cart
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeFromCart(@PathVariable("id") String cartItemId) {
        cartItemService.removeFromCart(cartItemId);
        return ResponseEntity.ok("Item removed from cart");
    }
}
