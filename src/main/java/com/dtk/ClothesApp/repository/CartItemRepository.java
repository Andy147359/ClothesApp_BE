package com.dtk.ClothesApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.dtk.ClothesApp.domain.entity.CartItem;

@Repository
public interface CartItemRepository extends
        JpaRepository<CartItem, String>, JpaSpecificationExecutor<CartItem> {

}
