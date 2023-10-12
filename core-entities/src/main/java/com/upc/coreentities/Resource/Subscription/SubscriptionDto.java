package com.upc.coreentities.Resource.Subscription;

import com.upc.coreentities.Resource.Plan.PlanDto;
import com.upc.coreentities.Security.Account;
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
    private Account account;
}
