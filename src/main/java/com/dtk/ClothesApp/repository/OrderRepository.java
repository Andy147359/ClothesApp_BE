package com.dtk.ClothesApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dtk.ClothesApp.domain.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
        @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
        List<Order> findOrdersByUserId(@Param("userId") String userId);
}
