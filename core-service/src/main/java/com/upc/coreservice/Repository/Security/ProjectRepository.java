package com.upc.coreservice.Repository.Security;

import com.upc.coreentities.Security.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findProjectById(Long id);
    List<Project> findAll();
    List<Project> findAllByBusinessProfileId(Long id);
    @Query(value = "SELECT p.* FROM project p " +
            "INNER JOIN proposal pr ON pr.id = p.proposal_id " +
            "INNER JOIN request re ON re.id = pr.request_id " +
            "INNER JOIN business_profile bp ON bp.id = re.business_profile_id " +
            "WHERE bp.id = :id", nativeQuery = true)
    List<Project> getAllByBusinessProfileId(@Param("id") Long id);
    @Query(value = "SELECT p.* FROM project p " +
            "INNER JOIN proposal pr ON pr.id = p.proposal_id " +
            "INNER JOIN request re ON re.id = pr.request_id " +
            "INNER JOIN user_profile up ON up.id = re.user_profile_id " +
            "WHERE up.id = :id", nativeQuery = true)
    List<Project> getAllByUserProfileId(@Param("id") Long id);
}
