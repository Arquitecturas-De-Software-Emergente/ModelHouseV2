package com.upc.modelhouse.SubscriptionAndPayment.resource.Checkout;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckoutItemDto {
    private String productName;
    private Integer quantity;
    private Double price;
    private Long productId;
    private Integer userId;
}
