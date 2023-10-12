package com.upc.coreentities.Resource.Proposal;

import com.upc.coreentities.Resource.ProjectActivity.CreateProjectActivityDto;
import com.upc.coreentities.Resource.ProjectResource.CreateProjectResourceDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateProposalDto {
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private String file;
    private CreateProjectActivityDto projectActivity;
    private CreateProjectResourceDto projectResource;

    /*
    @NotBlank
    @NotNull
    private String description;
    @NotNull
    private Float price;
    private String status;
    private Boolean isResponse;

     */
}
