package com.upc.coreservice.Service.Intefaces;

import com.upc.coreentities.ServiceManagement.ProjectActivity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectActivityService {
    List<ProjectActivity> findAllProposalId(Long id);
    ProjectActivity create(Long proposalId, ProjectActivity projectActivity);
    ResponseEntity<?> delete(Long id);
    ProjectActivity update(Long id, ProjectActivity proposal);
}
