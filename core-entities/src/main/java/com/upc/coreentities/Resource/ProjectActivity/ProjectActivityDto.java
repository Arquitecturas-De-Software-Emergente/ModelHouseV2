package com.upc.coreentities.Resource.ProjectActivity;

import com.upc.coreentities.Resource.Proposal.ProposalDto;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ProjectActivityDto {
    private Long id;
    private String description;
    private String image;
    private Date startedAt;
    private Date completedAt;
    private Boolean isChecked;
}
