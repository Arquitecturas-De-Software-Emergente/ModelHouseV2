package com.upc.coreservice.Repository.Security;

import com.upc.coreentities.Security.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Favorite findFavoriteById(Long id);
    List<Favorite> findAll();
    List<Favorite> findAllByBusinessProfileId(Long id);
    Boolean existsByUserProfileIdAndBusinessProfileId(Long userProfileId, Long businessProfileId);
    Favorite findByUserProfileIdAndBusinessProfileId(Long userProfileId, Long businessProfileId);
    List<Favorite> findAllByUserProfileId(Long id);
}
