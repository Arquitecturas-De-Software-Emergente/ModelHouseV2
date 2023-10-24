package com.upc.servicemanagement.ServiceManagement;

import com.upc.coreentities.Resource.ProjectResource.CreateProjectResourceDto;
import com.upc.coreentities.Resource.ProjectResource.ProjectResourceDto;
import com.upc.coreentities.Resource.ProjectResource.UpdateProjectResourceDto;
import com.upc.coreentities.ServiceManagement.ProjectActivity;
import com.upc.coreentities.ServiceManagement.ProjectResource;
import com.upc.coreentities.Util.Shared.exception.ResourceNotFoundException;
import com.upc.coreservice.Mapping.ProjectResourceMapper;
import com.upc.coreservice.Service.Interfaces.ProjectResourceService;
import com.upc.coreservice.Service.Interfaces.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SecurityRequirement(name = "acme")
@RestController
@CrossOrigin
@RequestMapping("")
public class ProjectResourceController {
    private final ProjectResourceService projectResourceService;
    private final ProjectResourceMapper mapper;

    private final StorageService storageService;
    private final HttpServletRequest request;

    public ProjectResourceController(ProjectResourceService projectResourceService, ProjectResourceMapper mapper, StorageService storageService, HttpServletRequest request) throws IOException {
        this.projectResourceService = projectResourceService;
        this.mapper = mapper;
        this.storageService = storageService;
        this.request = request;
        this.storageService.init("/ProjectResource");
    }
    private String uploadDir = "/Images/Resource";
    @GetMapping("/proposal/{proposalId}/project_resource")
    @Operation(tags = {"project-resource"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public List<ProjectResourceDto> getAllByProposalId(@PathVariable("proposalId") Long proposalId){
        return mapper.listToResource(projectResourceService.findAllProposalId(proposalId));
    }
    @PostMapping("/proposal/{proposalId}/project_resource")
    @Operation(tags = {"project-resource"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ProjectResourceDto createForProposal(@PathVariable("proposalId") Long proposalId,
                                    @RequestBody CreateProjectResourceDto createProjectResourceDto){
        return mapper.toResource(projectResourceService.createForProposal(proposalId, mapper.toModel(createProjectResourceDto)));
    }

    @PostMapping("/project/{projectId}/project_resource")
    @Operation(tags = {"project-resource"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ProjectResourceDto createForProject(@PathVariable("projectId") Long projectId,
                                                @RequestBody CreateProjectResourceDto createProjectResourceDto){
        return mapper.toResource(projectResourceService.createForProject(projectId, mapper.toModel(createProjectResourceDto)));
    }

    @PutMapping("/project_resource/{id}")
    @Operation(tags = {"project-resource"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ProjectResourceDto update(@PathVariable("id") Long id, @RequestBody UpdateProjectResourceDto resource){
        return mapper.toResource(projectResourceService.update(id, mapper.toModel(resource)));
    }

    @PostMapping("/project_resource/upload/{id}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    @Operation(tags = {"project-resource"})
    public Map<String, String> upload(@RequestParam("file") MultipartFile multipartFile, @PathVariable("id") Long id){
        ProjectResource projectResource = projectResourceService.findById(id);
        if(projectResource == null){
            throw new RuntimeException("Project Activity is null");
        }
        String path = storageService.store(multipartFile);
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/servicemanagement/media/")
                .path(path)
                .toUriString();
        projectResource.setImage(url);
        projectResourceService.update(id, projectResource);
        return Map.of("Url", url);
    }


    @GetMapping("/project_resource/media/{filename:.+}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    @Operation(tags = {"project-resource"})
    public ResponseEntity<Resource> upload(@PathVariable String filename) throws IOException {
        Resource file = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(file);
    }
    @DeleteMapping("/project_resource/{id}")
    @Operation(tags = {"project-resource"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return  projectResourceService.delete(id);
    }
}