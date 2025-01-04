package com.dtk.ClothesApp.service.impl;

import com.dtk.ClothesApp.domain.entity.Order;
import com.dtk.ClothesApp.domain.entity.OrderItem;
import com.dtk.ClothesApp.domain.entity.Product;
import com.dtk.ClothesApp.domain.entity.User;
import com.dtk.ClothesApp.domain.mapper.OrderMapper;
import com.dtk.ClothesApp.domain.request.Order.CreateOrderRequest;
import com.dtk.ClothesApp.domain.response.Order.OrderResponse;
import com.dtk.ClothesApp.repository.OrderItemRepository;
import com.dtk.ClothesApp.repository.OrderRepository;
import com.dtk.ClothesApp.repository.ProductRepository;
import com.dtk.ClothesApp.repository.UserRepository;
import com.dtk.ClothesApp.service.OrderService;
import com.dtk.ClothesApp.util.exception.IdInvalidExceptionHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IdInvalidExceptionHandler("User not found with id: " + request.getUserId()));

        // Tạo đối tượng Order
        Order order = orderMapper.createOrderRequestToOrder(request);
        order.setUser(user);
        // Lưu Order trước để có ID
        Order savedOrder = orderRepository.save(order);

        // Xử lý và lưu các OrderItem
        List<OrderItem> orderItems = request.getOrderItems().stream().map(orderItemRequest -> {
            Product product = productRepository.findById(orderItemRequest.getProductId())
                    .orElseThrow(() -> new IdInvalidExceptionHandler(
                            "Product not found with id: " + orderItemRequest.getProductId()));
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setOrder(savedOrder);
            return orderItemRepository.save(orderItem); // Lưu từng OrderItem
        }).collect(Collectors.toList());

        // Gán lại danh sách OrderItems vào Order để hiển thị ra api
        savedOrder.setOrderItems(orderItems);
        return orderMapper.orderToOrderResponse(savedOrder);
    }

    @Override
    public List<OrderResponse> getOrdersByUser(String userId) {
        return orderRepository.findOrdersByUserId(userId).stream()
                .map(orderMapper::orderToOrderResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse getOrderById(String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IdInvalidExceptionHandler("Order not found with id: " + id));
        return orderMapper.orderToOrderResponse(order);
    }

    @Override
    public void cancelOrder(String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IdInvalidExceptionHandler("Order not found with id: " + id));
        order.setStatus("cancelled");
        orderRepository.save(order);
    }

    @Override
    public void completeOrder(String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IdInvalidExceptionHandler("Order not found with id: " + id));
        order.setStatus("completed");
        orderRepository.save(order);
    }
}
