package com.upc.modelhouse.SubscriptionAndPayment.resource.Subscription;

import com.upc.modelhouse.SubscriptionAndPayment.resource.Plan.PlanDto;
import com.upc.modelhouse.security.resource.Account.AccountDto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {
    private Long id;
    private Date activatedAt;
    private Date updatedAt;
    private Boolean activated;
    private Boolean autoRenewal;
    private PlanDto plan;
    private AccountDto account;
}
