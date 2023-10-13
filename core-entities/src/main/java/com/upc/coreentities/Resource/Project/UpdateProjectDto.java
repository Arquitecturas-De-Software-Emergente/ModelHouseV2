package com.upc.coreentities.Resource.Project;

import com.upc.coreentities.Resource.ProjectActivity.CreateProjectActivityDto;
import com.upc.coreentities.Resource.ProjectResource.CreateProjectResourceDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProjectDto {
    private String title;
    private String description;
    private String image;
    private List<CreateProjectActivityDto> projectActivities;
    private List<CreateProjectResourceDto> projectResource;
}
