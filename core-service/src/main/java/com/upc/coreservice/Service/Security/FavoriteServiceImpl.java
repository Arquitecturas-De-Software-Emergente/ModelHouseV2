package com.upc.coreservice.Service.Security;

import com.upc.coreentities.Security.BusinessProfile;
import com.upc.coreentities.Security.Favorite;
import com.upc.coreentities.Security.UserProfile;
import com.upc.coreentities.Util.Shared.exception.ResourceValidationException;
import com.upc.coreservice.Repository.Security.BusinessProfileRepository;
import com.upc.coreservice.Repository.Security.FavoriteRepository;
import com.upc.coreservice.Repository.Security.UserProfileRepository;
import com.upc.coreservice.Service.Interfaces.FavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserProfileRepository userProfileRepository;
    private final BusinessProfileRepository businessProfileRepository;
    private final Validator validator;
    private static final String ENTITY = "Favorite";

    @Override
    public List<Favorite> getFavorites(Long userProfileId) {
        return favoriteRepository.findAllByUserProfileId(userProfileId);
    }

    @Override
    public Favorite addFavorite(Long userProfileId, Long businessProfileId, Favorite favorite) {
        Set<ConstraintViolation<Favorite>> violations = validator.validate(favorite);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        UserProfile userProfile = userProfileRepository.findById(userProfileId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        BusinessProfile businessProfile = businessProfileRepository.findById(businessProfileId)
                .orElseThrow(() -> new EntityNotFoundException("Business not found"));

        if (favoriteRepository.existsByUserProfileIdAndBusinessProfileId(userProfileId, businessProfileId)) {
            throw new FavoriteAlreadyExistsException("This business is already in your favorites");
        }

        favorite.setUserProfile(userProfile);
        favorite.setBusinessProfile(businessProfile);
        favoriteRepository.save(favorite);

        return favorite;
    }

    @Override
    public ResponseEntity<?> deleteFavorite(Long id) {
        Optional<Favorite> favorite = favoriteRepository.findById(id);
        if (favorite.isPresent()) {
            favoriteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public static class FavoriteAlreadyExistsException extends RuntimeException {

        public FavoriteAlreadyExistsException(String message) {
            super(message);
        }
    }
}
