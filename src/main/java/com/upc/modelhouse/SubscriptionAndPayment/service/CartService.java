package com.upc.modelhouse.SubscriptionAndPayment.service;

import com.upc.modelhouse.SubscriptionAndPayment.domain.persistence.CartRepository;
import com.upc.modelhouse.SubscriptionAndPayment.resource.Cart.CartItemNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void deleteCartItem(int id,int userId) throws CartItemNotExistException {
        if (!cartRepository.existsById(id))
            throw new CartItemNotExistException("Cart id is invalid : " + id);
        cartRepository.deleteById(id);
    }
    public void deleteCartItems(int userId) {
        cartRepository.deleteAll();
    }




}
