package com.upc.coreservice.Repository.Security;

import com.upc.coreentities.Security.BusinessProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessProfileRepository extends JpaRepository<BusinessProfile, Long> {
    List<BusinessProfile> findAll();
    BusinessProfile findBusinessProfileById(Long id);
    BusinessProfile findBusinessProfileByAccount_Id(Long id);

    @Query(
        value ="SELECT b from BusinessProfile b " +
                "where ((b.name like concat('%',:filter, '%') ) " +
                "or (b.address like concat('%',:filter, '%') ))"
    )
    List<BusinessProfile> getFilterBusinessProfile(
            @Param("filter") String filter
    );

    @Query(value = "SELECT a.deviceId " +
            "from Account a right join BusinessProfile bp " +
            "ON a.id = :accountId")
    String getDeviceIdByBusinessProfile(Long accountId);
}
