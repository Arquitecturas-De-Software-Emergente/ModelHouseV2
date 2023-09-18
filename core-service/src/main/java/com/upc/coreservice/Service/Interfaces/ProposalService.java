package com.upc.coreservice.Service.Interfaces;

import com.upc.coreentities.ServiceManagement.Proposal;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProposalService {
    List<Proposal> getAll();
    Proposal findAllRequestId(Long id);
    Proposal create(Long requestId, Proposal request);
    ResponseEntity<?> delete(Long id);
    Proposal update(Long id, Proposal request);
}
