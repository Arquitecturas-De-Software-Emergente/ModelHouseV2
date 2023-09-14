package com.upc.coreentities.Resource.Product;
public class ProductNotExistException extends IllegalArgumentException{
    public ProductNotExistException(String msg) {
        super(msg);
    }
}
