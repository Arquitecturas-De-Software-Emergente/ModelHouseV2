package com.upc.coreentities.Resource.Proposal;

import com.upc.coreentities.Resource.ProjectActivity.CreateProjectActivityDto;
import com.upc.coreentities.Resource.ProjectResource.CreateProjectResourceDto;
import com.upc.coreentities.Resource.Request.RequestDto;
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
   // private Float price;
    private String status;
    private List<CreateProjectActivityDto> projectActivities;
    private List<CreateProjectResourceDto> projectResource;
  //  private Boolean isResponse;
  //  private Date responseDate;
   // private RequestDto request;
}
