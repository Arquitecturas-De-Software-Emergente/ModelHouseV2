package com.upc.coreservice.Service.Interfaces;

import com.upc.coreentities.Resource.ProjectResource.ProjectResourceDto;
import com.upc.coreentities.Resource.ProjectResource.UpdateProjectResourceDto;
import com.upc.coreentities.ServiceManagement.ProjectActivity;
import com.upc.coreentities.ServiceManagement.ProjectResource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectResourceService {
    ProjectResource findById(Long id);
    List<ProjectResource> findAllProposalId(Long id);
    //ProjectResource create(Long proposalId, ProjectResource projectResource);
    ProjectResource createForProposal(Long projectId, ProjectResource projectResource);
    ProjectResource createForProject(Long projectId, ProjectResource projectResource);
    ResponseEntity<?> delete(Long id);
    ProjectResource update(Long id, ProjectResourceDto projectResource);
}
