package com.upc.modelhouse.ServiceManagement.api;

import com.upc.modelhouse.ServiceManagement.domain.service.ProjectResourceService;
import com.upc.modelhouse.ServiceManagement.mapping.ProjectResourceMapper;
import com.upc.modelhouse.ServiceManagement.resource.ProjectResource.CreateProjectResourceDto;
import com.upc.modelhouse.ServiceManagement.resource.ProjectResource.ProjectResourceDto;
import com.upc.modelhouse.ServiceManagement.resource.ProjectResource.UpdateProjectResourceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "acme")
@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ProjectResourceController {
    private final ProjectResourceService projectResourceService;
    private final ProjectResourceMapper mapper;

    public ProjectResourceController(ProjectResourceService projectResourceService, ProjectResourceMapper mapper) {
        this.projectResourceService = projectResourceService;
        this.mapper = mapper;
    }
    @GetMapping("/proposal/{proposalId}/project_resource")
    @Operation(tags = {"project-resource"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public List<ProjectResourceDto> getAllByProposalId(@PathVariable("proposalId") Long proposalId){
        return mapper.listToResource(projectResourceService.findAllProposalId(proposalId));
    }
    @PostMapping("/proposal/{proposalId}/project_resource")
    @Operation(tags = {"project-resource"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ProjectResourceDto create(@PathVariable("proposalId") Long proposalId,
                                    @RequestBody CreateProjectResourceDto createProjectResourceDto){
        return mapper.toResource(projectResourceService.create(proposalId, mapper.toModel(createProjectResourceDto)));
    }
    @PutMapping("/project_resource/{id}")
    @Operation(tags = {"project-resource"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ProjectResourceDto update(@PathVariable("id") Long id, @RequestBody UpdateProjectResourceDto updateProjectResourceDto){
        return mapper.toResource(projectResourceService.update(id, mapper.toModel(updateProjectResourceDto)));
    }
    @DeleteMapping("/project_resource/{id}")
    @Operation(tags = {"project-resource"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return  projectResourceService.delete(id);
    }
}