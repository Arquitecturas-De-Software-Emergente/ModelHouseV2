package com.upc.modelhouse.SubscriptionAndPayment.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.upc.modelhouse.SubscriptionAndPayment.resource.Checkout.CheckoutPlanDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Value("${BASE_URL}")
    private String baseURL;
    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;
    public Session createSession(List<CheckoutPlanDto> checkoutPlanDtoList) throws StripeException {
        /* Success and Failure */
        String successURL = baseURL+ "payment/success";
        String failureURL = baseURL+ "payment/failure";
        Stripe.apiKey = apiKey;

        List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();

        for(CheckoutPlanDto checkoutPlanDto: checkoutPlanDtoList){
            sessionItemList.add(createSessionLineItem(checkoutPlanDto));
        }
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failureURL)
                .setSuccessUrl(successURL)
                .addAllLineItem(sessionItemList)
                .build();
        return Session.create(params);
    }

    private SessionCreateParams.LineItem createSessionLineItem(CheckoutPlanDto checkoutPlanDto) {
        return SessionCreateParams.LineItem.builder()
                .setPriceData(createPriceData(checkoutPlanDto))
                .setQuantity(Long.parseLong(String.valueOf(1))) /* Just 1 Plan per buying */
                .build();
    }

    private SessionCreateParams.LineItem.PriceData createPriceData(CheckoutPlanDto checkoutPlanDto) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmount((long) (checkoutPlanDto.getPrice()*100))
                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName(checkoutPlanDto.getName()).build()).build();
    }
}
