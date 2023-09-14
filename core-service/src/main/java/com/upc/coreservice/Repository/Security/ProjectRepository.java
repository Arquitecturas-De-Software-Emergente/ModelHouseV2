package com.upc.coreservice.Repository.Security;

import com.upc.coreentities.Security.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findProjectById(Long id);
    List<Project> findAll();
    List<Project> findAllByBusinessProfileId(Long id);
}
