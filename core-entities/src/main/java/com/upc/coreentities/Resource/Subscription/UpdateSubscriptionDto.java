package com.upc.coreentities.Resource.Subscription;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSubscriptionDto {
    private Date activatedAt;
    private Date updatedAt;
    private Boolean activated;
    private Boolean autoRenewal;
}
