package com.upc.modelhouse.SubscriptionAndPayment.resource.Product;
public class ProductNotExistException extends IllegalArgumentException{
    public ProductNotExistException(String msg) {
        super(msg);
    }
}
