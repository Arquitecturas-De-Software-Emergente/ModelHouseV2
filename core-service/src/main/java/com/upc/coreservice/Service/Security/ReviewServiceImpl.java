package com.upc.coreservice.Service.Security;

import com.upc.coreentities.Resource.Review.CreateReviewDto;
import com.upc.coreentities.Security.BusinessProfile;
import com.upc.coreentities.Security.Project;
import com.upc.coreentities.Security.Review;
import com.upc.coreentities.Security.UserProfile;
import com.upc.coreentities.Util.Shared.exception.ResourceNotFoundException;
import com.upc.coreentities.Util.Shared.exception.ResourceValidationException;
import com.upc.coreservice.Repository.Security.BusinessProfileRepository;
import com.upc.coreservice.Repository.Security.ProjectRepository;
import com.upc.coreservice.Repository.Security.ReviewRepository;
import com.upc.coreservice.Repository.Security.UserProfileRepository;
import com.upc.coreservice.Service.Interfaces.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProjectRepository projectRepository;
    private final UserProfileRepository userProfileRepository;
    private final Validator validator;
    private static final String ENTITY = "Review";

    @Override
    public Review getByProjectId(Long projectId) {
        return reviewRepository.findByProjectId(projectId);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent()) {
            reviewRepository.delete(review.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Review create(Long projectId, Long useProfileId, Review review) {
        Set<ConstraintViolation<Review>> violations = validator.validate(review);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        try {
            Project project = projectRepository.findProjectById(projectId);
            if (project == null)
                throw new ResourceNotFoundException("Project", projectId);
            UserProfile userProfile = userProfileRepository.getById(useProfileId);
            if(userProfile == null)
                throw new ResourceNotFoundException("User Profile", useProfileId);
            review.setProject(project);
            review.setUserProfile(userProfile);
            review.setReviewDate(new Date());
            return reviewRepository.save(review);
        }catch (Exception e){
            throw new IllegalArgumentException("Error in the service review ", e);
        }
    }


}
