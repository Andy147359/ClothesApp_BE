package com.dtk.ClothesApp.domain.mapper;

import com.dtk.ClothesApp.domain.entity.OrderItem;
import com.dtk.ClothesApp.domain.response.Order.OrderResponse.OrderItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "product.id", target = "product.id")
    @Mapping(source = "product.name", target = "product.name")
    @Mapping(source = "product.price", target = "product.price")
    OrderItemResponse orderItemToOrderItemResponse(OrderItem orderItem);
}
