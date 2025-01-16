package com.dtk.ClothesApp.domain.mapper;

import com.dtk.ClothesApp.domain.entity.CartItem;
import com.dtk.ClothesApp.domain.response.CartItem.CartItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemResponse cartItemToCartItemResponse(CartItem cartItem);
}
