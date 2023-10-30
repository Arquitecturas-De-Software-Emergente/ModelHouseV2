package com.upc.coreservice.Mapping;

import com.upc.coreentities.Resource.ProjectActivity.CreateProjectActivityDto;
import com.upc.coreentities.Resource.ProjectActivity.ProjectActivityDto;
import com.upc.coreentities.Resource.ProjectActivity.UpdateProjectActivityDto;
import com.upc.coreentities.ServiceManagement.ProjectActivity;
import com.upc.coreentities.Util.Shared.mapping.EnhancedModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@AllArgsConstructor
public class ProjectActivityMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;
    public ProjectActivityDto toResource(ProjectActivity model){
        return mapper.map(model, ProjectActivityDto.class);
    }
    public List<ProjectActivityDto> listToResource(List<ProjectActivity> model){
        return mapper.mapList(model, ProjectActivityDto.class);
    }
    public ProjectActivity toModel(ProjectActivityDto resource) {
        return mapper.map(resource, ProjectActivity.class);
    }

    public ProjectActivity toModel(CreateProjectActivityDto resource) {
        return mapper.map(resource, ProjectActivity.class);
    }
    public ProjectActivityDto toModel(ProjectActivity resource) {
        return mapper.map(resource, ProjectActivityDto.class);
    }
    public ProjectActivity toModel(UpdateProjectActivityDto resource) {
        return mapper.map(resource, ProjectActivity.class);
    }
}
