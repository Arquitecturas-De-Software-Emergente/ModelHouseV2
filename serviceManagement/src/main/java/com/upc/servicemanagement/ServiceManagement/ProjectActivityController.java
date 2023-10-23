package com.upc.servicemanagement.ServiceManagement;

import com.upc.coreentities.Resource.ProjectActivity.CreateProjectActivityDto;
import com.upc.coreentities.Resource.ProjectActivity.ProjectActivityDto;
import com.upc.coreentities.ServiceManagement.ProjectActivity;
import com.upc.coreservice.Mapping.ProjectActivityMapper;
import com.upc.coreservice.Service.Interfaces.ProjectActivityService;
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
import java.util.List;
import java.util.Map;

@SecurityRequirement(name = "acme")
@RestController
@CrossOrigin
@RequestMapping("")
public class ProjectActivityController {
    private final ProjectActivityService projectActivityService;
    private final ProjectActivityMapper mapper;
    private final StorageService storageService;
    private final HttpServletRequest request;
    public ProjectActivityController(ProjectActivityService projectActivityService, ProjectActivityMapper mapper, StorageService storageService, HttpServletRequest request) throws IOException {
        this.projectActivityService = projectActivityService;
        this.mapper = mapper;
        this.storageService = storageService;
        this.request = request;
        this.storageService.init("/ProjectActivity");
    }

    private String uploadDir = "/Images/Activity";
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
        return mapper.toResource(projectActivityService.createForProposal(proposalId, mapper.toModel(createProjectActivityDto)));
    }
    @PostMapping("/project_activity/upload/{id}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    @Operation(tags = {"project-activity"})
    public Map<String, String> upload(@RequestParam("file") MultipartFile multipartFile, @PathVariable("id") Long id){
        ProjectActivity projectActivity = projectActivityService.findById(id);
        if(projectActivity == null){
            throw new RuntimeException("Project Activity is null");
        }
        String path = storageService.store(multipartFile);
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/service-management/project_resource/media/")
                .path(path)
                .toUriString();
        projectActivityService.update(id, projectActivity);
        return Map.of("Url", url);
    }
    @GetMapping("/project_activity/media/{filename:.+}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    @Operation(tags = {"project-activity"})
    public ResponseEntity<Resource> upload(@PathVariable String filename) throws IOException {
        Resource file = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(file);
    }
    @DeleteMapping("/project_activity/{id}")
    @Operation(tags = {"project-activity"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return  projectActivityService.delete(id);
    }
}
