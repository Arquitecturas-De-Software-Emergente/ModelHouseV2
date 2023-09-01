package com.upc.modelhouse.SubscriptionAndPayment.api;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.upc.modelhouse.SubscriptionAndPayment.resource.Checkout.CheckoutPlanDto;
import com.upc.modelhouse.SubscriptionAndPayment.resource.Checkout.StripeResponse;
import com.upc.modelhouse.SubscriptionAndPayment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
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
