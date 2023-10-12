package com.upc.coreservice.Repository.Security;

import com.upc.coreentities.Security.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    @Override
    List<UserProfile> findAll();
    UserProfile findUserProfileById(Long id);
    //UserProfile findUserProfileById(Long id);
}
