package com.dtk.ClothesApp.service;

import com.dtk.ClothesApp.domain.request.Order.CreateOrderRequest;
import com.dtk.ClothesApp.domain.response.Order.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(CreateOrderRequest request);

    List<OrderResponse> getOrdersByUser(String userId);

    OrderResponse getOrderById(String id);

    void cancelOrder(String id);

    void completeOrder(String id);
}
