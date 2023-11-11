package com.upc.coreservice.Service.ServiceManagement;

import com.upc.coreentities.Security.UserProfile;
import com.upc.coreentities.ServiceManagement.Proposal;
import com.upc.coreentities.ServiceManagement.Request;
import com.upc.coreentities.Util.Shared.exception.ResourceNotFoundException;
import com.upc.coreentities.Util.Shared.exception.ResourceValidationException;
import com.upc.coreservice.Repository.Security.BusinessProfileRepository;
import com.upc.coreservice.Repository.Security.UserProfileRepository;
import com.upc.coreservice.Repository.ServiceManagement.ProposalRepository;
import com.upc.coreservice.Repository.ServiceManagement.RequestRepository;
import com.upc.coreservice.Service.Interfaces.RequestService;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final ProposalRepository proposalRepository;
    private final BusinessProfileRepository businessProfileRepository;
    private final UserProfileRepository userProfileRepository;
    private final Validator validator;
    private static final String ENTITY = "Request";

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository, ProposalRepository proposalRepository, BusinessProfileRepository businessProfileRepository, UserProfileRepository userProfileRepository, Validator validator) {
        this.requestRepository = requestRepository;
        this.proposalRepository = proposalRepository;
        this.businessProfileRepository = businessProfileRepository;
        this.userProfileRepository = userProfileRepository;
        this.validator = validator;
    }

    @Override
    public List<Request> getAll() {
        return requestRepository.findAll();
    }

    @Override
    public List<Request> findAllBusinessProfileId(Long id) {
        return requestRepository.findAllByBusinessProfileId(id);
    }

    @Override
    public List<Request> findAllUserProfileId(Long id) {
        return requestRepository.findAllByBusinessProfileId(id);
    }


    @Override
    public List<Request> findAllBusinessProfileIdAndStatus(Long id, String status) {
        return requestRepository.findAllByBusinessProfileIdAndRequestStatus(id, status);
    }



    @Override
    public List<Request> findAllUserProfileIdAndStatus(Long id, String status) {
        return requestRepository.findAllByUserProfileIdAndRequestStatus(id, status);
    }


    @Override
    public Request create(Long userId, Long businessId, Request request) {
        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return businessProfileRepository.findById(businessId).map(businessProfile -> {
            UserProfile userProfile = userProfileRepository.findUserProfileById(userId);
            if (userProfile == null)
                throw new ResourceNotFoundException("The client does not exist");
            if (businessProfile.getAccount().getId().equals(userProfile.getAccount().getId()))
                throw new ResourceNotFoundException("The company cannot make a request to it");
            request.setUserProfile(userProfile);
            request.setBusinessProfile(businessProfile);
            request.setRequestStatus("Pendiente");

            return requestRepository.save(request);
        }).orElseThrow(() -> new ResourceNotFoundException("BusinessProfile", businessId));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return requestRepository.findById(id).map(request -> {
            requestRepository.delete(request);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }


    @Override
    public Request changeStatus(Long id, Request request) {
        System.out.println("Entro 0");
        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return requestRepository.findById(id).map(change -> {
            String newStatus = request.getRequestStatus();
            String originalStatus = change.getRequestStatus();

            if ("Aprobado".equals(newStatus) && !"Aprobado".equals(originalStatus)) {
                System.out.println("Entro");
                Proposal proposal = new Proposal();
                proposal.setTitle(change.getTitle());
                proposal.setDescription(change.getDescription());
                proposal.setBusinessProfile(change.getBusinessProfile());
                proposal.setProposalStatus("Pendiente");
                proposal.setRequest(change);
                proposalRepository.save(proposal);
            }

            change.setRequestStatus(newStatus);

            return requestRepository.save(change);
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

}

