package com.dtk.ClothesApp.domain.mapper;

import com.dtk.ClothesApp.domain.entity.Order;
import com.dtk.ClothesApp.domain.request.Order.CreateOrderRequest;
import com.dtk.ClothesApp.domain.response.Order.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "userId", target = "user.id")
    Order createOrderRequestToOrder(CreateOrderRequest request);

    OrderResponse orderToOrderResponse(Order order);
}
