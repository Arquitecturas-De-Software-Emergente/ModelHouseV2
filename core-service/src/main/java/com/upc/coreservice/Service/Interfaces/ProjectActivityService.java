package com.upc.coreservice.Service.Interfaces;

import com.upc.coreentities.ServiceManagement.ProjectActivity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectActivityService {
    ProjectActivity findById(Long id);
    List<ProjectActivity> findAllProposalId(Long id);
    ProjectActivity create(Long proposalId, ProjectActivity projectActivity);
    ResponseEntity<?> delete(Long id);
    ProjectActivity update(Long id, ProjectActivity proposal);
}
