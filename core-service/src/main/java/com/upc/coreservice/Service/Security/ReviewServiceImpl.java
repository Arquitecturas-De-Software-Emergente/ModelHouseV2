package com.upc.coreservice.Service.Security;

import com.upc.coreentities.Resource.Review.CreateReviewDto;
import com.upc.coreentities.Security.Project;
import com.upc.coreentities.Security.Review;
import com.upc.coreentities.Util.Shared.exception.ResourceNotFoundException;
import com.upc.coreentities.Util.Shared.exception.ResourceValidationException;
import com.upc.coreservice.Repository.Security.ProjectRepository;
import com.upc.coreservice.Repository.Security.ReviewRepository;
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
    private final Validator validator;
    private static final String ENTITY = "Review";

    @Override
    public List<Review> getAllByProjectId(Long projectId) {
        return reviewRepository.findAllByProjectId(projectId);
    }

    @Override
    public Review create(Long projectId, Review review) {
        Set<ConstraintViolation<Review>> violations = validator.validate(review);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Project project = projectRepository.findProjectById(projectId);
        if (project == null)
            throw new ResourceNotFoundException("Project", projectId);
        review.setProject(project);
        project.setReviewScore(review.getScore());
        project.setReviewComment(review.getComment());
        review.setReviewDate(new Date());
        return reviewRepository.save(review);
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


}
