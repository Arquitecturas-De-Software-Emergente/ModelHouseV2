package com.upc.modelhouse.SubscriptionAndPayment.resource.Subscription;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateSubscriptionDto {
    private Boolean activated;
    private Boolean autoRenewal;
}
