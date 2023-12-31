package com.upc.coreentities.Resource.Plan;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePlanDto {
    private String name;
    private String description;
    private Double price;
    private Long maxUsers;
}
