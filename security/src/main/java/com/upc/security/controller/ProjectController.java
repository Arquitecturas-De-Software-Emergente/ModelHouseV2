
package com.upc.security.controller;


import com.upc.coreentities.Resource.Project.CreateProjectDto;
import com.upc.coreentities.Resource.Project.ProjectDto;
import com.upc.coreentities.Resource.Project.UpdateProjectDto;
import com.upc.coreentities.Security.Project;
import com.upc.coreservice.Mapping.ProjectMapper;
import com.upc.coreservice.Service.Interfaces.ProjectService;
import com.upc.coreservice.Service.Interfaces.StorageService;
import io.swagger.v3.oas.annotations.Operation;
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
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("")
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectMapper mapper;
    private final StorageService storageService;
    private final HttpServletRequest request;

    public ProjectController(ProjectService projectService, ProjectMapper mapper, StorageService storageService, HttpServletRequest request) throws IOException {
        this.projectService = projectService;
        this.mapper = mapper;
        this.storageService = storageService;
        this.request = request;
        this.storageService.init("/Project");
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
    @PutMapping("/project/{id}")
    @Operation(tags = {"Project"})
    public UpdateProjectDto update(@PathVariable("id") Long id, @RequestBody UpdateProjectDto projectDto){
        return this.projectService.updateProject(id, projectDto);
    }
    @PutMapping("/project/{id}/status/{status}")
    @Operation(tags = {"Project"})
    public ProjectDto updateStatus(@PathVariable("id") Long id, @PathVariable("status") String status){
        return mapper.toModel(this.projectService.changeStatus(id, status));
    }
    @DeleteMapping("/project/{id}")
    @Operation(tags = {"Project"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Long id){
        return  projectService.deleteProject(id);
    }

    @PostMapping("/project/upload/{id}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    @Operation(tags = {"Project"})
    public Map<String, String> upload(@RequestParam("file") MultipartFile multipartFile, @PathVariable("id") Long id){
        Project project = projectService.findById(id);
        if(project == null){
            throw new RuntimeException("Project is null");
        }
        String path = storageService.store(multipartFile);
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/security/project/media/")
                .path(path)
                .toUriString();
        project.setImage(url);
        projectService.update(id, project);
        return Map.of("Url", url);
    }
    @GetMapping("/project/media/{filename:.+}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    @Operation(tags = {"Project"})
    public ResponseEntity<Resource> upload(@PathVariable String filename) throws IOException {
        Resource file = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(file);
    }

}
