package com.dtk.ClothesApp.service.impl;

import com.dtk.ClothesApp.domain.entity.CartItem;
import com.dtk.ClothesApp.domain.entity.Product;
import com.dtk.ClothesApp.domain.entity.User;
import com.dtk.ClothesApp.domain.mapper.CartItemMapper;
import com.dtk.ClothesApp.domain.request.CartItem.CreateCartItemRequest;
import com.dtk.ClothesApp.domain.request.CartItem.UpdateCartItemRequest;
import com.dtk.ClothesApp.domain.response.CartItem.CartItemResponse;
import com.dtk.ClothesApp.repository.CartItemRepository;
import com.dtk.ClothesApp.repository.ProductRepository;
import com.dtk.ClothesApp.repository.UserRepository;
import com.dtk.ClothesApp.service.CartItemService;
import com.dtk.ClothesApp.util.exception.IdInvalidExceptionHandler;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    @Transactional
    public CartItemResponse addToCart(CreateCartItemRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IdInvalidExceptionHandler("User not found with id: " + request.getUserId()));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(
                        () -> new IdInvalidExceptionHandler("Product not found with id: " + request.getProductId()));

        Optional<CartItem> productExistInUserCart = productExistInUserCart(request.getUserId(), request.getProductId());
        if (productExistInUserCart.isPresent()) {
            productExistInUserCart.get()
                    .setQuantity(productExistInUserCart.get().getQuantity() + request.getQuantity());

            CartItem updatedCartItem = cartItemRepository.save(productExistInUserCart.get());
            return cartItemMapper.cartItemToCartItemResponse(updatedCartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());

            CartItem savedCartItem = cartItemRepository.save(cartItem);
            return cartItemMapper.cartItemToCartItemResponse(savedCartItem);
        }
    }

    @Override
    public Optional<CartItem> productExistInUserCart(String userId, String productId) {
        return cartItemRepository.findByUserIdAndProductId(userId, productId);
    }

    @Override
    public List<CartItemResponse> getCartItemsByUser(String userId) {
        return cartItemRepository.findByUserId(userId)
                .stream()
                .map(cartItemMapper::cartItemToCartItemResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CartItemResponse updateCartItemQuantity(String cartItemId, UpdateCartItemRequest request) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IdInvalidExceptionHandler("Cart item not found with id: " + cartItemId));

        cartItem.setQuantity(request.getQuantity());
        CartItem updatedCartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.cartItemToCartItemResponse(updatedCartItem);
    }

    @Override
    @Transactional
    public CartItemResponse updateQuantityByUserAndProductId(CreateCartItemRequest request) {
        Optional<CartItem> productExistInUserCart = productExistInUserCart(request.getUserId(), request.getProductId());
        if (productExistInUserCart.isPresent()) {
            productExistInUserCart.get().setQuantity(request.getQuantity());
            CartItem updatedCartItem = cartItemRepository.save(productExistInUserCart.get());
            return cartItemMapper.cartItemToCartItemResponse(updatedCartItem);
        } else {
            throw new IdInvalidExceptionHandler("Cart item not found with user id: " + request.getUserId()
                    + " and product id: " + request.getProductId());
        }
    }

    @Override
    @Transactional
    public void removeFromCart(String id) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new IdInvalidExceptionHandler("Cart item not found with id: " + id));
        cartItemRepository.delete(cartItem);
    }
}
