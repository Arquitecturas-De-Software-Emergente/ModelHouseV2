package com.upc.coreservice.Service.Interfaces;

import com.upc.coreentities.Security.BusinessProfile;

import java.util.List;

public interface BusinessProfileService {
    List<BusinessProfile> findAll();
    List<BusinessProfile> findByFilter(String filter);
    BusinessProfile findByAccountId(Long userId);
    BusinessProfile findById(Long id);
    BusinessProfile create(Long userId, BusinessProfile businessProfile);
    BusinessProfile update(Long id, BusinessProfile businessProfile);
    String getDeviceId(Long businessProfileId);
}
