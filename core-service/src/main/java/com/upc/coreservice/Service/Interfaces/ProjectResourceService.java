package com.upc.coreservice.Service.Interfaces;

import com.upc.coreentities.ServiceManagement.ProjectResource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectResourceService {
    ProjectResource findById(Long id);
    List<ProjectResource> findAllProposalId(Long id);
    ProjectResource create(Long proposalId, ProjectResource projectResource);
    ResponseEntity<?> delete(Long id);
    ProjectResource update(Long id, ProjectResource projectResource);
}
