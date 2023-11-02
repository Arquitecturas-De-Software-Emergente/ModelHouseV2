package com.upc.coreservice.Service.Security;

import com.mysql.cj.conf.PropertyKey;
import com.upc.coreentities.Resource.Project.ProjectDto;
import com.upc.coreentities.Resource.Project.UpdateProjectDto;
import com.upc.coreentities.Security.Project;
import com.upc.coreentities.Security.UserProfile;
import com.upc.coreentities.ServiceManagement.Proposal;
import com.upc.coreentities.Util.Shared.exception.ResourceNotFoundException;
import com.upc.coreentities.Util.Shared.exception.ResourceValidationException;
import com.upc.coreservice.Repository.Security.BusinessProfileRepository;
import com.upc.coreservice.Repository.Security.ProjectRepository;
import com.upc.coreservice.Repository.ServiceManagement.ProjectActivityRepository;
import com.upc.coreservice.Repository.ServiceManagement.ProjectResourceRepository;
import com.upc.coreservice.Service.Interfaces.ProjectActivityService;
import com.upc.coreservice.Service.Interfaces.ProjectResourceService;
import com.upc.coreservice.Service.Interfaces.ProjectService;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final BusinessProfileRepository businessProfileRepository;
    private final ProjectResourceService projectResourceService;
    private final ProjectActivityService projectActivityService;
    private final Validator validator;
    private static final String ENTITY = "Project";

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> findAllByBusinessProfileId(Long id) {
        return projectRepository.findAllByBusinessProfileId(id);
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findProjectById(id);
    }

    @Override
    public Project createProject(Long businessId, Project project, Proposal proposal) {
        Set<ConstraintViolation<Project>> violations = validator.validate(project);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return businessProfileRepository.findById(businessId).map(businessProfile -> {
            project.setBusinessProfile(businessProfile);
            project.setProposal(proposal);
            return projectRepository.save(project);
        }).orElseThrow(() -> new ResourceNotFoundException("BusinessProfile", businessId));
    }
    @Override
    public Project createProject(Long businessId, Project project) {
        Set<ConstraintViolation<Project>> violations = validator.validate(project);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        project.setStatus("Proceso");
        return businessProfileRepository.findById(businessId).map(businessProfile -> {
            project.setBusinessProfile(businessProfile);
            project.setProposal(null);
            return projectRepository.save(project);
        }).orElseThrow(() -> new ResourceNotFoundException("BusinessProfile", businessId));
    }
    @Override
    public UpdateProjectDto updateProject(Long id, UpdateProjectDto project) {
        Set<ConstraintViolation<UpdateProjectDto>> violations = validator.validate(project);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        try{
            Project optionalProject = projectRepository.getById(id);
            optionalProject.setTitle(project.getTitle());
            optionalProject.setDescription(project.getDescription());
            project.getProjectResourceDtos().forEach(resource -> projectResourceService.update(resource.getId(), resource));
            project.getProjectActivityDtos().forEach(activity -> projectActivityService.update(activity.getId(), activity));
            this.projectRepository.save(optionalProject);
            return project;
        }catch (Exception e){
            throw new ResourceNotFoundException(ENTITY, id);
        }
    }

    @Override
    public ResponseEntity<?> deleteProject(Long id) {
         return projectRepository.findById(id).map(project -> {
             projectRepository.delete(project);
             return ResponseEntity.ok().build();
         }).orElseThrow(()->new ResourceNotFoundException(ENTITY, id));

    }

    @Override
    public Project update(Long id, Project project) {
        Set<ConstraintViolation<Project>> violations = validator.validate(project);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return projectRepository.findById(id).map(project1 ->
                        projectRepository.save(project1.withImage(project1.getImage())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public Project changeStatus(Long id, String status) {
        Project project = projectRepository.getById(id);
        if(project == null)
            throw new ResourceNotFoundException(ENTITY, id);

        try{
            project.setStatus(status);
            projectRepository.save(project);
            return project;
        }catch (Exception e){
            throw new ResourceNotFoundException("Ocurrió un error al actualizar", id);
        }
    }
}
