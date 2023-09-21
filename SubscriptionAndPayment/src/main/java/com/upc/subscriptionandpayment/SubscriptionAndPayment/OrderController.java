package com.upc.subscriptionandpayment.SubscriptionAndPayment;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.upc.coreentities.Resource.Checkout.CheckoutPlanDto;
import com.upc.coreentities.Resource.Checkout.StripeResponse;
import com.upc.coreservice.Service.SubscriptionAndPayment.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /* Stripe Session Checkout API */
    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutPlanDto> checkoutPlanDtoList)
            throws StripeException {
        Session session = orderService.createSession(checkoutPlanDtoList);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
    }
}
