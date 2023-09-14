package com.upc.coreservice.Repository.ServiceManagement;

import com.upc.coreentities.ServiceManagement.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    Proposal findProposalById(Long id);
    List<Proposal> findAll();
    Proposal findByRequestId(Long id);
}
