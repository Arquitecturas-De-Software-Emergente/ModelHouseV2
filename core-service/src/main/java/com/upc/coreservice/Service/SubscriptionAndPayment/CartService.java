package com.upc.coreservice.Service.SubscriptionAndPayment;

import com.upc.coreentities.Resource.Cart.CartItemNotExistException;
import com.upc.coreservice.Repository.SubscriptionAndPayment.CartRepository;
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
