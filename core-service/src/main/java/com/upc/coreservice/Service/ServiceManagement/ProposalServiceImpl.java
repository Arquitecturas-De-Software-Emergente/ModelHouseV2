package com.upc.coreservice.Service.ServiceManagement;

import com.upc.coreentities.ServiceManagement.Proposal;
import com.upc.coreentities.Util.Shared.exception.ResourceNotFoundException;
import com.upc.coreentities.Util.Shared.exception.ResourceValidationException;
import com.upc.coreservice.Repository.ServiceManagement.ProposalRepository;
import com.upc.coreservice.Repository.ServiceManagement.RequestRepository;
import com.upc.coreservice.Service.Interfaces.ProposalService;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ProposalServiceImpl implements ProposalService {
    private final ProposalRepository proposalRepository;
    private final RequestRepository requestRepository;
    private final Validator validator;
    private static final String ENTITY = "Proposal";

    public ProposalServiceImpl(ProposalRepository proposalRepository, RequestRepository requestRepository, Validator validator) {
        this.proposalRepository = proposalRepository;
        this.requestRepository = requestRepository;
        this.validator = validator;
    }

    @Override
    public List<Proposal> getAll() {
        return proposalRepository.findAll();
    }

    @Override
    public Proposal findAllRequestId(Long id) {
        return proposalRepository.findByRequestId(id);
    }

    @Override
    public Proposal create(Long requestId, Proposal proposal) {
        Set<ConstraintViolation<Proposal>> violations = validator.validate(proposal);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        proposal.setProposalDate(new Date());
        Proposal proposalExist = proposalRepository.findByRequestId(requestId);
        if(proposalExist != null)
            throw new ResourceNotFoundException("Proposal is exist");
        return requestRepository.findById(requestId).map(request -> {
            proposal.setRequest(request);
            return proposalRepository.save(proposal);
        }).orElseThrow(() -> new ResourceNotFoundException("Request", requestId));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return proposalRepository.findById(id).map(proposal -> {
            proposalRepository.delete(proposal);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public Proposal update(Long id, Proposal request) {
        return null;
    }
}
