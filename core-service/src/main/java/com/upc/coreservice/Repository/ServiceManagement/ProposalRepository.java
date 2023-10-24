package com.upc.coreservice.Repository.ServiceManagement;

import com.upc.coreentities.Security.BusinessProfile;
import com.upc.coreentities.ServiceManagement.Proposal;
import com.upc.coreentities.ServiceManagement.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    Proposal findProposalById(Long id);
    Proposal findALlByProposalStatus(String status);
    List<Proposal> findAll();
    Proposal findByRequestId(Long id);
}
