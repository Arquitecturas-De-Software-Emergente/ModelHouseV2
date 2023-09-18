package com.upc.coreentities.Resource.Proposal;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
