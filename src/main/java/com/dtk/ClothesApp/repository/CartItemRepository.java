package com.dtk.ClothesApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dtk.ClothesApp.domain.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {

        List<CartItem> findByUserId(String userId);

        Optional<CartItem> findByUserIdAndProductId(String userId, String productId);
}
