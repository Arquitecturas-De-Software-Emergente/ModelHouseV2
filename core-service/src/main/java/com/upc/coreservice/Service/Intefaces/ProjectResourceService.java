package com.upc.coreservice.Service.Intefaces;

import com.upc.coreentities.ServiceManagement.ProjectResource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectResourceService {
    List<ProjectResource> findAllProposalId(Long id);
    ProjectResource create(Long proposalId, ProjectResource projectResource);
    ResponseEntity<?> delete(Long id);
    ProjectResource update(Long id, ProjectResource projectResource);
}
