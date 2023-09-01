package com.upc.modelhouse.SubscriptionAndPayment.resource.Cart;
public class CartItemNotExistException extends IllegalArgumentException {
    public CartItemNotExistException(String msg) {
        super(msg);
    }
}
