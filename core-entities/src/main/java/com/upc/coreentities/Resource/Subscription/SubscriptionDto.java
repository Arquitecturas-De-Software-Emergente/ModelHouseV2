package com.upc.coreentities.Resource.Subscription;

import com.upc.coreentities.Resource.Account.AccountDto;
import com.upc.coreentities.Resource.Plan.PlanDto;
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
