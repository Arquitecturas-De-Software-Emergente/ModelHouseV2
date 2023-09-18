package com.upc.coreservice.Service.Interfaces;

import com.upc.coreentities.Security.UserProfile;

import java.util.List;

public interface UserProfileService {
    List<UserProfile> findAll();
    UserProfile findByUserId(Long accountId);
    UserProfile create(Long accountId, UserProfile userProfile);
    UserProfile update(Long id, UserProfile userProfile);

}
