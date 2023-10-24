package com.upc.coreentities.Resource.ProjectResource;

import com.upc.coreentities.Resource.Proposal.ProposalDto;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResourceDto {
    private Long id;
    private Number quantity;
    private String image;
    private String resourceType;
    private Boolean isChecked;

}
