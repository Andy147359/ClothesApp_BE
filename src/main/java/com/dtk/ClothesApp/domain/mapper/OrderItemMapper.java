package com.dtk.ClothesApp.domain.mapper;

import com.dtk.ClothesApp.domain.entity.OrderItem;
import com.dtk.ClothesApp.domain.response.Order.OrderResponse.OrderItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemResponse orderItemToOrderItemResponse(OrderItem orderItem);
}
