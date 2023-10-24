package com.upc.coreentities.Resource.Proposal;

import com.upc.coreentities.Resource.ProjectActivity.CreateProjectActivityDto;
import com.upc.coreentities.Resource.ProjectResource.CreateProjectResourceDto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ProposalDto {
    private Long id;
    private Date proposalDate;
    private String description;
    private String status;
    private List<ProjectActivityDto> projectActivities;
    private List<ProjectResourceDto> projectResources;
    private RequestDto request;
}
