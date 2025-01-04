package com.dtk.ClothesApp.domain.mapper;

import com.dtk.ClothesApp.domain.entity.CartItem;
import com.dtk.ClothesApp.domain.response.CartItem.CartItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(source = "user.id", target = "user.id")
    @Mapping(source = "user.fullName", target = "user.fullName")
    @Mapping(source = "user.email", target = "user.email")
    @Mapping(source = "product.id", target = "product.id")
    @Mapping(source = "product.name", target = "product.name")
    @Mapping(source = "product.price", target = "product.price")
    @Mapping(source = "product.discountPrice", target = "product.discountPrice")
    @Mapping(source = "product.description", target = "product.description")
    @Mapping(source = "product.stock", target = "product.stock")
    @Mapping(source = "product.imageUrl", target = "product.imageUrl")
    CartItemResponse cartItemToCartItemResponse(CartItem cartItem);
}
