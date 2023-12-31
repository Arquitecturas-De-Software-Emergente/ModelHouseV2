package com.upc.coreservice.Repository.ServiceManagement;

import com.upc.coreentities.Security.BusinessProfile;
import com.upc.coreentities.ServiceManagement.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    Request findRequestById(Long id);
    List<Request> findAllByBusinessProfileId(Long businessProfile_id);
    List<Request> findAllByUserProfileId(Long userProfile_id);
    List<Request> findAll();
    List<Request> findAllByUserProfileIdAndRequestStatus(Long id, String status);
    List<Request> findAllByBusinessProfileIdAndRequestStatus(Long id, String status);
}
