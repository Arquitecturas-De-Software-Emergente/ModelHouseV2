package com.upc.coreentities.Resource.Project;

import com.upc.coreentities.Resource.ProjectActivity.ProjectActivityDto;
import com.upc.coreentities.Resource.ProjectResource.ProjectResourceDto;
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
    private List<ProjectActivityDto> projectActivities;
    private List<ProjectResourceDto> projectResources;

}
