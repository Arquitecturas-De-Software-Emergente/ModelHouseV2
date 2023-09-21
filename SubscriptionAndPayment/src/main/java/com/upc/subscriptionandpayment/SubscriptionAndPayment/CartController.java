package com.upc.subscriptionandpayment.SubscriptionAndPayment;

import com.upc.coreentities.Resource.Cart.CartDto;
import com.upc.coreentities.Resource.Cart.CartItemDto;
import com.upc.coreentities.Resource.Cart.CartRequest;
import com.upc.coreentities.Security.Account;
import com.upc.coreentities.SubscriptionAndPayment.Cart;
import com.upc.coreentities.SubscriptionAndPayment.Plan;
import com.upc.coreservice.Repository.Security.AccountRepository;
import com.upc.coreservice.Repository.SubscriptionAndPayment.CartRepository;
import com.upc.coreservice.Repository.SubscriptionAndPayment.PlanRepository;
import com.upc.coreservice.Service.SubscriptionAndPayment.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
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
