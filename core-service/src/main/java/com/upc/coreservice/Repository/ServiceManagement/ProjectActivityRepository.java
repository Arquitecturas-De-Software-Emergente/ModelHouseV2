package com.upc.coreservice.Repository.ServiceManagement;

import com.upc.coreentities.ServiceManagement.ProjectActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectActivityRepository extends JpaRepository<ProjectActivity, Long> {
    ProjectActivity findProjectActivityById(Long id);
    List<ProjectActivity> findAll();
    List<ProjectActivity> findAllByProposalId(Long id);
    List<ProjectActivity> findAllByProjectId(Long id);
}
