package com.upc.modelhouse.security.api;

import com.upc.modelhouse.security.domain.service.ProjectService;
import com.upc.modelhouse.security.mapping.ProjectMapper;
import com.upc.modelhouse.security.resource.Project.CreateProjectDto;
import com.upc.modelhouse.security.resource.Project.ProjectDto;
import com.upc.modelhouse.security.resource.Project.UpdateProjectDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectMapper mapper;

    public ProjectController(ProjectService projectService, ProjectMapper mapper) {
        this.projectService = projectService;
        this.mapper = mapper;
    }

    @GetMapping("/project")
    @Operation(tags = {"Project"})
    public List<ProjectDto> getAll(){
        return mapper.listToResource(projectService.getAll());
    }
    @GetMapping("/business_profile/{businessId}/project")
    @Operation(tags = {"Project"})
    public List<ProjectDto> getAllByBusinessId(@PathVariable("businessId") Long id){
        return mapper.listToResource(projectService.findAllByBusinessProfileId(id));
    }
    @GetMapping("/project/{id}/profile")
    @Operation(tags = {"Project"})
    public ProjectDto getById(@PathVariable("id") Long id){
        return mapper.toResource(projectService.findById(id));
    }
    @PostMapping("/business_profile/{businessId}/project")
    @Operation(tags = {"Project"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ProjectDto createProject(@PathVariable("businessId") Long businessId, @RequestBody CreateProjectDto createProjectDto){
        return mapper.toResource(projectService.createProject(businessId, mapper.toModel(createProjectDto)));
    }
    @PutMapping("/project/{id}")
    @Operation(tags = {"Project"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ProjectDto updateProject(@PathVariable("id") Long id, @RequestBody UpdateProjectDto updateBusinessProfileDto){
        return mapper.toResource(projectService.updateProject(id, mapper.toModel(updateBusinessProfileDto)));
    }
    @DeleteMapping("/project/{id}")
    @Operation(tags = {"Project"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Long id){
        return  projectService.deleteProject(id);
    }
}
