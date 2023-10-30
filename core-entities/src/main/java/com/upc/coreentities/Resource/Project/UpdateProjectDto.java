package com.upc.coreentities.Resource.Project;

import com.upc.coreentities.Resource.ProjectActivity.CreateProjectActivityDto;
import com.upc.coreentities.Resource.ProjectActivity.ProjectActivityDto;
import com.upc.coreentities.Resource.ProjectResource.CreateProjectResourceDto;
import com.upc.coreentities.Resource.ProjectResource.ProjectResourceDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProjectDto {
    private String title;
    private String description;
    private List<ProjectResourceDto> projectResourceDtos = new ArrayList<>();
    private List<ProjectActivityDto> projectActivityDtos = new ArrayList<>();
}
