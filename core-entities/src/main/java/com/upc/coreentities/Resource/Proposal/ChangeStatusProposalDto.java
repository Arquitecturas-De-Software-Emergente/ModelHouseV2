package com.upc.coreentities.Resource.Proposal;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ChangeStatusProposalDto {
    @NotNull
    @NotBlank
    private String status;
}
