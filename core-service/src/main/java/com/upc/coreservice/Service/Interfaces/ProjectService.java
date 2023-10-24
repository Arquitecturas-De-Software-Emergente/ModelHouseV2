package com.upc.coreservice.Service.Interfaces;

import com.upc.coreentities.Resource.Project.ProjectDto;
import com.upc.coreentities.Security.Project;
import com.upc.coreentities.Security.UserProfile;
import com.upc.coreentities.ServiceManagement.Proposal;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectService {
    List<Project> getAll();
    List<Project> findAllByBusinessProfileId(Long proposalId);
    Project findById(Long id);
    Project createProject(Long businessId, Project project, Proposal proposal);

    Project createProject(Long businessId, Project project);
    Project updateProject(Long id, Project project);
    ResponseEntity<?> deleteProject(Long id);

    Project update(Long id, Project project);
}
