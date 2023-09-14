package com.upc.coreentities.Resource.ProjectResource;

import com.upc.coreentities.Resource.Proposal.ProposalDto;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResourceDto {
    private Long Id;
    private String description;
    private Number quantity;
    private String state;
    private ProposalDto proposal;
}
