package com.upc.servicemanagement.ServiceManagement;

import com.upc.coreentities.Resource.Review.CreateReviewDto;
import com.upc.coreentities.Resource.Review.ReviewDto;
import com.upc.coreservice.Mapping.ReviewMapper;
import com.upc.coreservice.Service.Interfaces.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper mapper;

    public ReviewController(ReviewService reviewService, ReviewMapper mapper) {
        this.reviewService = reviewService;
        this.mapper = mapper;
    }

    @GetMapping("/review/{projectId}")
    @Operation(tags = {"Review"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ReviewDto getAllByUserId(@PathVariable("projectId") Long projectId){
        return mapper.toResource(reviewService.getByProjectId(projectId));
    }

    @PostMapping("/project/{projectId}/user_profile/{userProfileId}/review")
    @Operation(tags = {"Review"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ReviewDto createReview(@PathVariable("projectId") Long projectId,
                                  @PathVariable("userProfileId") Long userProfileId,
                                  @RequestBody CreateReviewDto reviewDto){
        return mapper.toResource(reviewService.create(projectId, userProfileId, mapper.toModel(reviewDto)));
    }

    @DeleteMapping("/review/{id}")
    @Operation(tags = {"Review"})
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<?> deleteReview(@PathVariable("id") Long id){
        return reviewService.delete(id);
    }

}
