package com.upc.coreservice.Service.Interfaces;

import com.upc.coreentities.Security.Review;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {
    Review getByProjectId(Long projectId);
    Review create(Long projectId, Long businessProfileId, Review review);
    ResponseEntity<?> delete(Long id);
  //  Review update(Long id, Review review);
}
