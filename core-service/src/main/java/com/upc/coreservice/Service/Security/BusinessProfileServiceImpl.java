package com.upc.coreservice.Service.Security;

import com.upc.coreentities.Security.BusinessProfile;
import com.upc.coreentities.Util.Shared.exception.ResourceNotFoundException;
import com.upc.coreentities.Util.Shared.exception.ResourceValidationException;
import com.upc.coreservice.Repository.Security.AccountRepository;
import com.upc.coreservice.Repository.Security.BusinessProfileRepository;
import com.upc.coreservice.Service.Interfaces.BusinessProfileService;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class BusinessProfileServiceImpl implements BusinessProfileService {
    private final BusinessProfileRepository businessProfileRepository;
    private final AccountRepository accountRepository;
    private final Validator validator;
    private static final String ENTITY = "BusinessProfile";
    @Override
    public List<BusinessProfile> findAll() {
        return businessProfileRepository.findAll();
    }

    @Override
    public BusinessProfile findByAccountId(Long accountId) {
        return businessProfileRepository.findBusinessProfileByAccount_Id(accountId);
    }

    @Override
    public BusinessProfile findById(Long id) {
        return businessProfileRepository.findBusinessProfileById(id);
    }

    @Override
    public BusinessProfile create(Long accountId, BusinessProfile businessProfile) {
        Set<ConstraintViolation<BusinessProfile>> violations = validator.validate(businessProfile);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        BusinessProfile businessProfileExist = businessProfileRepository.findBusinessProfileByAccount_Id(accountId);
        if(businessProfileExist != null)
            throw new ResourceNotFoundException("Business Profile is exist");
        return accountRepository.findById(accountId).map(account -> {
            businessProfile.setAccount(account);

            return businessProfileRepository.save(businessProfile);
        }).orElseThrow(() -> new ResourceNotFoundException("Request", accountId));
    }

    @Override
    public BusinessProfile update(Long id, BusinessProfile businessProfile) {
        Set<ConstraintViolation<BusinessProfile>> violations = validator.validate(businessProfile);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return businessProfileRepository.findById(id).map(profile ->
                        businessProfileRepository.save(profile.withName(businessProfile.getName())
                                .withDescription(businessProfile.getDescription())
                                .withImage(businessProfile.getImage())
                                .withAddress(businessProfile.getAddress())
                                .withWebSite(businessProfile.getWebSite())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
