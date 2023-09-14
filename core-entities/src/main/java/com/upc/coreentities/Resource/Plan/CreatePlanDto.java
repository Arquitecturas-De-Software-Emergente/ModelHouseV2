package com.upc.coreentities.Resource.Plan;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
