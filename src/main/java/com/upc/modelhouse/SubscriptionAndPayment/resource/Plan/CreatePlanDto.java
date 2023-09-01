package com.upc.modelhouse.SubscriptionAndPayment.resource.Plan;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlanDto {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private Double price;
    @NotNull
    @NotBlank
    private Long maxUsers;
}
