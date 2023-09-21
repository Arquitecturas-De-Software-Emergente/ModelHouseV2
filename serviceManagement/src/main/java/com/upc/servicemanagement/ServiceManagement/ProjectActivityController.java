package com.upc.servicemanagement.ServiceManagement;

import com.upc.coreentities.Resource.ProjectActivity.CreateProjectActivityDto;
import com.upc.coreentities.Resource.ProjectActivity.ProjectActivityDto;
import com.upc.coreentities.Resource.ProjectActivity.UpdateProjectActivityDto;
import com.upc.coreservice.Mapping.ProjectActivityMapper;
import com.upc.coreservice.Service.Interfaces.ProjectActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "acme")
@RestController
@CrossOrigin
@RequestMapping("")
public class ProjectActivityController {
    private final ProjectActivityService projectActivityService;
    private final ProjectActivityMapper mapper;

    public ProjectActivityController(ProjectActivityService projectActivityService, ProjectActivityMapper mapper) {
        this.projectActivityService = projectActivityService;
        this.mapper = mapper;
    }
    @GetMapping("/proposal/{proposalId}/project_activity")
    @Operation(tags = {"project-activity"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public List<ProjectActivityDto> getAllByProposalId(@PathVariable("proposalId") Long proposalId){
        return mapper.listToResource(projectActivityService.findAllProposalId(proposalId));
    }
    @PostMapping("/proposal/{proposalId}/project_activity")
    @Operation(tags = {"project-activity"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ProjectActivityDto create(@PathVariable("proposalId") Long proposalId,
                                            @RequestBody CreateProjectActivityDto createProjectActivityDto){
        return mapper.toResource(projectActivityService.create(proposalId, mapper.toModel(createProjectActivityDto)));
    }
    @PutMapping("/project_activity/{id}")
    @Operation(tags = {"project-activity"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ProjectActivityDto update(@PathVariable("id") Long id, @RequestBody UpdateProjectActivityDto updateProjectActivityDto){
        return mapper.toResource(projectActivityService.update(id, mapper.toModel(updateProjectActivityDto)));
    }
    @DeleteMapping("/project_activity/{id}")
    @Operation(tags = {"project-activity"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return  projectActivityService.delete(id);
    }
}
