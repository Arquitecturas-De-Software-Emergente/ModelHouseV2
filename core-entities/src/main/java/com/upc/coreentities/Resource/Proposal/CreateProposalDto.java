package com.upc.coreentities.Resource.Proposal;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateProposalDto {
    @NotBlank
    @NotNull
    private String description;
    @NotNull
    private Float price;
    private String status;
    private Boolean isResponse;
}
