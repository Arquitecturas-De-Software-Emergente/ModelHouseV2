package com.upc.modelhouse.SubscriptionAndPayment.resource.Plan;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PlanDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long maxUsers;
}
