package com.upc.coreservice.Service.Security;

import com.upc.coreentities.Security.UserProfile;
import com.upc.coreentities.Util.Shared.exception.ResourceNotFoundException;
import com.upc.coreentities.Util.Shared.exception.ResourceValidationException;
import com.upc.coreservice.Repository.Security.AccountRepository;
import com.upc.coreservice.Repository.Security.UserProfileRepository;
import com.upc.coreservice.Service.Interfaces.UserProfileService;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final AccountRepository userRepository;
    private final Validator validator;
    private static final String ENTITY = "UserProfile";
    @Override
    public List<UserProfile> findAll() {
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfile findByUserId(Long accountId) {
        return userProfileRepository.findUserProfileByAccountId(accountId);
    }

    @Override
    public UserProfile create(Long accountId, UserProfile userProfile) {
        Set<ConstraintViolation<UserProfile>> violations = validator.validate(userProfile);
        System.out.println(userProfile);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        System.out.println(userProfile);
        UserProfile userProfileExist = userProfileRepository.findUserProfileByAccountId(accountId);
        System.out.println(userProfile);
        if(userProfileExist != null)
            throw new ResourceNotFoundException("User Profile is exist");
        System.out.println(userProfile);
        return userRepository.findById(accountId).map(user -> {
            userProfile.setAccount(user);
            return userProfileRepository.save(userProfile);
        }).orElseThrow(() -> new ResourceNotFoundException("Request", accountId));

    }

    @Override
    public UserProfile update(Long id, UserProfile userProfile) {
        Set<ConstraintViolation<UserProfile>> violations = validator.validate(userProfile);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return userProfileRepository.findById(id).map(profile ->
                        userProfileRepository.save(profile.withFirstName(userProfile.getFirstName())
                                .withLastName(userProfile.getLastName())
                                .withGender(userProfile.getGender())
                                .withAddress(userProfile.getAddress())
                                .withPhoneNumber(userProfile.getPhoneNumber())
                                .withLastLogin(new Date())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public String getDeviceId(Long useProfileId) {
        System.out.println("Algo mas");
        try {
            System.out.println("Algo mas 2");
            UserProfile userProfile = userProfileRepository.getById(useProfileId);
            System.out.println("Algo mas 3");
            return userProfileRepository.getDeviceIdByUserProfile(userProfile.getAccount().getId());
        }catch (Exception e){
            throw new ResourceValidationException("Error in the service get device id", e);
        }
    }
}
