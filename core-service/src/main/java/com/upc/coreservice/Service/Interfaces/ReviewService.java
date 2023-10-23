package com.upc.coreservice.Service.Interfaces;

import com.upc.coreentities.Security.Review;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {
    List<Review> getAllByProjectId(Long projectId);
    Review create(Long projectId, Review review);
    ResponseEntity<?> delete(Long id);
  //  Review update(Long id, Review review);
}
