package com.upc.modelhouse.SubscriptionAndPayment.resource.Cart;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CartDto {
    private List<CartItemDto> cartItems;
    private double totalCost;
    public CartDto(List<CartItemDto> cartItemDtoList, double totalCost) {
        this.cartItems = cartItemDtoList;
        this.totalCost = totalCost;
    }
}
