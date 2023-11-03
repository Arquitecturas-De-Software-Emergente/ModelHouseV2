package com.upc.subscriptionandpayment.SubscriptionAndPayment;

import com.stripe.model.Event;
import com.stripe.net.ApiResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class StripeWebhookController {
    @PostMapping("/webhook")
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload) {
        try {
            Event event = ApiResource.GSON.fromJson(payload, Event.class);
            // Handle the event
            if ("payment_intent.succeeded".equals(event.getType())) {
                // Handle payment_intent.succeeded event
                System.out.println("PaymentIntent was successful!");

            } else if ("payment_method.attached".equals(event.getType())) {
                // Handle payment_method.attached event
                System.out.println("PaymentMethod was attached to a Customer!");
            } else {
                // Handle other event types
                System.out.println("Unhandled event type: " + event.getType());
            }
            // Acknowledge receipt of the event
            return ResponseEntity.status(HttpStatus.OK).body("Success");
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}