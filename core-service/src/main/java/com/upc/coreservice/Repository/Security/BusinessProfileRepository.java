package com.upc.coreservice.Repository.Security;

import com.upc.coreentities.Security.BusinessProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessProfileRepository extends JpaRepository<BusinessProfile, Long> {
    List<BusinessProfile> findAll();
    BusinessProfile findBusinessProfileById(Long id);
    BusinessProfile findBusinessProfileByAccount_Id(Long id);
}
