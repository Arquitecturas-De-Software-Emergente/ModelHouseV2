package com.upc.modelhouse.SubscriptionAndPayment.resource.Checkout;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckoutPlanDto {
    private Long planId;
    private String name;
    private String description;
    private Double price;
}
