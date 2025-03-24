package com.dtk.ClothesApp.controller;

import com.dtk.ClothesApp.domain.request.Order.CreateOrderRequest;
import com.dtk.ClothesApp.domain.response.Order.OrderResponse;
import com.dtk.ClothesApp.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(request));
    }

    @GetMapping("/user")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable String id) {
        orderService.cancelOrder(id);
        return ResponseEntity.ok("Order canceled successfully");
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<String> completeOrder(@PathVariable String id) {
        orderService.completeOrder(id);
        return ResponseEntity.ok("Order completed successfully");
    }
}
