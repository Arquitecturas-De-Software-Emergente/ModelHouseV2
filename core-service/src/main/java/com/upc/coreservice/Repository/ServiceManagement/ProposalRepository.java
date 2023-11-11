package com.upc.coreservice.Repository.ServiceManagement;

import com.upc.coreentities.Security.BusinessProfile;
import com.upc.coreentities.ServiceManagement.Proposal;
import com.upc.coreentities.ServiceManagement.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    Proposal findProposalById(Long id);
    Proposal findALlByProposalStatus(String status);
    Proposal findByRequestId(Long id);
    List<Proposal> findAll();
    @Query(value = "SELECT pr.* FROM proposal pr " +
            "INNER JOIN request re ON re.id = pr.request_id " +
            "INNER JOIN business_profile bp ON bp.id = re.business_profile_id " +
            "WHERE bp.id = :id", nativeQuery = true)
    List<Proposal> getAllByBusinessProfileId(@Param("id") Long id);

    @Query(value = "SELECT pr.* FROM proposal pr " +
            "INNER JOIN request re ON re.id = pr.request_id " +
            "INNER JOIN user_profile up ON up.id = re.user_profile_id " +
            "WHERE up.id = :id", nativeQuery = true)
    List<Proposal> getAllByUserProfileId(@Param("id") Long id);
}
