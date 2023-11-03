package com.upc.coreentities.Resource.Project;

import com.upc.coreentities.Resource.Proposal.ProposalDto;

import com.upc.coreentities.ServiceManagement.ProjectActivity;
import com.upc.coreentities.ServiceManagement.ProjectResource;
import lombok.*;

import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private Long businessProfileId;
    private String status;
    private List<ProjectActivity> projectActivities;
    private List<ProjectResource> projectResources;
    private ProposalDto proposal;
}
