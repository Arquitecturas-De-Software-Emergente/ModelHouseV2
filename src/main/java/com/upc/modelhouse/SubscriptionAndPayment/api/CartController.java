package com.upc.modelhouse.SubscriptionAndPayment.api;

import com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity.Cart;
import com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity.Plan;
import com.upc.modelhouse.SubscriptionAndPayment.domain.persistence.CartRepository;
import com.upc.modelhouse.SubscriptionAndPayment.domain.persistence.PlanRepository;
import com.upc.modelhouse.SubscriptionAndPayment.resource.Cart.CartDto;
import com.upc.modelhouse.SubscriptionAndPayment.resource.Cart.CartItemDto;
import com.upc.modelhouse.SubscriptionAndPayment.resource.Cart.CartRequest;
import com.upc.modelhouse.SubscriptionAndPayment.service.CartService;
import com.upc.modelhouse.security.domain.model.entity.Account;
import com.upc.modelhouse.security.domain.persistence.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PlanRepository planRepository;

    @GetMapping("/carts/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Integer cartId){
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/cart")
    public CartDto getCartItems(){
        List<Cart> carts = cartRepository.findAll();
        List<CartItemDto> cartItems = new ArrayList<>();
        double totalCost = 0;

        // Calculate the total cost and populate the cart items list
        for (Cart cart : carts) {
            Plan plan = cart.getPlan();
            int quantity = cart.getQuantity();
            double itemTotalCost = plan.getPrice() * quantity;
            totalCost += itemTotalCost;

            CartItemDto cartItem = new CartItemDto(plan, quantity);
            cartItems.add(cartItem);
        }
        return new CartDto(cartItems, totalCost);
    }

    @PostMapping("/addCart")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cart> createCart(@RequestBody CartRequest cartRequest)  {

        Account account = accountRepository.findAccountById(cartRequest.getAccountId());
        Plan plan = planRepository.findById(cartRequest.getPlanId())
                .orElseThrow(()->new NotFoundException("Plan not found"));
        Cart cart = new Cart(plan,1,account);

        Cart savedCart = cartRepository.save(cart);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCart);
    }
}
