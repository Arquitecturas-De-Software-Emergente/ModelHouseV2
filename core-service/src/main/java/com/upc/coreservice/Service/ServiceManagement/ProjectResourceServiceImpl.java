package com.upc.coreservice.Service.ServiceManagement;

import com.upc.coreentities.ServiceManagement.ProjectResource;
import com.upc.coreentities.Util.Shared.exception.ResourceNotFoundException;
import com.upc.coreentities.Util.Shared.exception.ResourceValidationException;
import com.upc.coreservice.Repository.Security.ProjectRepository;
import com.upc.coreservice.Repository.ServiceManagement.ProjectResourceRepository;
import com.upc.coreservice.Service.Interfaces.ProjectResourceService;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProjectResourceServiceImpl implements ProjectResourceService {
    private final ProjectRepository projectRepository;
    private final ProjectResourceRepository projectResourceRepository;
    private final Validator validator;
    private static final String ENTITY = "Request";

    @Override
    public List<ProjectResource> findAllProposalId(Long id) {
        return projectResourceRepository.findAllByProjectId(id);
    }

    @Override
    public ProjectResource create(Long projectId, ProjectResource projectResource) {
        Set<ConstraintViolation<ProjectResource>> violations = validator.validate(projectResource);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return projectRepository.findById(projectId).map(project -> {
            projectResource.setProject(project);
            return projectResourceRepository.save(projectResource);
        }).orElseThrow(() -> new ResourceNotFoundException("Proposal", projectId));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return projectResourceRepository.findById(id).map(projectResource -> {
            projectResourceRepository.delete(projectResource);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(ENTITY, id));

    }

    @Override
    public ProjectResource update(Long id, ProjectResource projectResource) {
        Set<ConstraintViolation<ProjectResource>> violations = validator.validate(projectResource);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return projectResourceRepository.findById(id).map(project ->
                        projectResourceRepository.save(project.withDescription(projectResource.getDescription())
                                .withQuantity(projectResource.getQuantity())
                                .withState(projectResource.getState())))
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY , id));
    }
}
