package com.upc.coreservice.Mapping;

import com.upc.coreentities.Resource.Project.CreateProjectDto;
import com.upc.coreentities.Resource.Project.ProjectDto;
import com.upc.coreentities.Resource.Project.UpdateProjectDto;
import com.upc.coreentities.Security.Project;
import com.upc.coreentities.Util.Shared.mapping.EnhancedModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@AllArgsConstructor
public class ProjectMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ProjectDto toResource(Project model){
        return mapper.map(model, ProjectDto.class);
    }
    public List<ProjectDto> listToResource(List<Project> model){
        return mapper.mapList(model, ProjectDto.class);
    }
    public Project toModel(ProjectDto resource) {
        return mapper.map(resource, Project.class);
    }

    public Project toModel(CreateProjectDto resource) {
        return mapper.map(resource, Project.class);
    }

    public Project toModel(UpdateProjectDto resource) {
        return mapper.map(resource, Project.class);
    }

    public ProjectDto toModel(Project resource) {
        return mapper.map(resource, ProjectDto.class);
    }
}
