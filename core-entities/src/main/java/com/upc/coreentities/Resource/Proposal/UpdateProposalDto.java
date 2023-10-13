package com.upc.coreentities.Resource.Proposal;

import com.upc.coreentities.Resource.ProjectActivity.CreateProjectActivityDto;
import com.upc.coreentities.Resource.ProjectResource.CreateProjectResourceDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProposalDto {
    private String title;
    private String description;
    private String file;

    /*
    private Date proposalDate;
    private String description;
 //   private Float price;
    private String status;
    private Boolean isResponse;
    private Date responseDate;

     */
}
