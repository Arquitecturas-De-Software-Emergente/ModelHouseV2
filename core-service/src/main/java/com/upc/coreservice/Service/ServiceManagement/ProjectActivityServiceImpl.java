package com.upc.coreservice.Service.ServiceManagement;

import com.upc.coreentities.ServiceManagement.ProjectActivity;
import com.upc.coreentities.Util.Shared.exception.ResourceNotFoundException;
import com.upc.coreentities.Util.Shared.exception.ResourceValidationException;
import com.upc.coreservice.Repository.Security.ProjectRepository;
import com.upc.coreservice.Repository.ServiceManagement.ProjectActivityRepository;
import com.upc.coreservice.Service.Interfaces.ProjectActivityService;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProjectActivityServiceImpl implements ProjectActivityService {
    private final ProjectRepository projectRepository;
    private final ProjectActivityRepository projectActivityRepository;
    private final Validator validator;
    private static final String ENTITY = "ProjectActivity";

    @Override
    public ProjectActivity findById(Long id) {
        return projectActivityRepository.getById(id);
    }

    @Override
    public List<ProjectActivity> findAllProposalId(Long id) {
        return projectActivityRepository.findAllByProjectId(id);
    }

    @Override
    public ProjectActivity create(Long projectId, ProjectActivity projectActivity) {
        Set<ConstraintViolation<ProjectActivity>> violations = validator.validate(projectActivity);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return projectRepository.findById(projectId).map(project -> {
            projectActivity.setProject(project);
            return projectActivityRepository.save(projectActivity);
        }).orElseThrow(() -> new ResourceNotFoundException("Proposal", projectId));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return projectActivityRepository.findById(id).map(projectActivity -> {
            projectActivityRepository.delete(projectActivity);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(ENTITY, id));

    }

    @Override
    public ProjectActivity update(Long id, ProjectActivity projectActivity) {
        Set<ConstraintViolation<ProjectActivity>> violations = validator.validate(projectActivity);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return projectActivityRepository.findById(id).map(projectResource ->
                        projectActivityRepository.save(projectResource.withDescription(projectActivity.getDescription())
                                .withStatus(projectActivity.getStatus())))
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY , id));

    }

}