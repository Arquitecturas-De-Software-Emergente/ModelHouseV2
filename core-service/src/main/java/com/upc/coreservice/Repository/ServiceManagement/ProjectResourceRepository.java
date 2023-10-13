package com.upc.coreservice.Repository.ServiceManagement;

import com.upc.coreentities.ServiceManagement.ProjectActivity;
import com.upc.coreentities.ServiceManagement.ProjectResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectResourceRepository extends JpaRepository<ProjectResource, Long> {
    ProjectResource findProjectResourceById(Long id);
    List<ProjectResource> findAll();
    List<ProjectResource> findAllByProposalId(Long id);
    List<ProjectResource> findAllByProjectId(Long id);
}
