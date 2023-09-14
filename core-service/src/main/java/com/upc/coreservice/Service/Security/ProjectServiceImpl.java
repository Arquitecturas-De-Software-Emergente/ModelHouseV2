package com.upc.coreservice.Service.Security;

import com.upc.coreentities.Security.Project;
import com.upc.coreentities.ServiceManagement.Proposal;
import com.upc.coreentities.Util.Shared.exception.ResourceNotFoundException;
import com.upc.coreentities.Util.Shared.exception.ResourceValidationException;
import com.upc.coreservice.Repository.Security.BusinessProfileRepository;
import com.upc.coreservice.Repository.Security.ProjectRepository;
import com.upc.coreservice.Service.Intefaces.ProjectService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final BusinessProfileRepository businessProfileRepository;
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

        return businessProfileRepository.findById(businessId).map(businessProfile -> {
            project.setBusinessProfile(businessProfile);
            project.setProposal(null);
            return projectRepository.save(project);
        }).orElseThrow(() -> new ResourceNotFoundException("BusinessProfile", businessId));
    }
    @Override
    public Project updateProject(Long id, Project project) {
        Set<ConstraintViolation<Project>> violations = validator.validate(project);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return projectRepository.findById(id).map(change ->
                projectRepository.save(change.withTitle(project.getTitle())
                        .withDescription(project.getDescription())
                        .withImage(project.getImage())))
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY , id));
    }

    @Override
    public ResponseEntity<?> deleteProject(Long id) {
         return projectRepository.findById(id).map(project -> {
             projectRepository.delete(project);
             return ResponseEntity.ok().build();
         }).orElseThrow(()->new ResourceNotFoundException(ENTITY, id));

    }
}
