package com.upc.coreservice.Repository.Security;

import com.upc.coreentities.Security.UserProfile;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    @Override
    List<UserProfile> findAll();
    UserProfile findUserProfileById(Long id);
    UserProfile findUserProfileByAccountId(Long id);
    @Query(value = "SELECT a.deviceId " +
            "from Account a WHERE a.id = :accountId")
    String getDeviceIdByUserProfile(Long accountId);
}
