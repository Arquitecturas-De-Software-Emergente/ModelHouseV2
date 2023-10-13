package com.upc.coreservice.Service.Interfaces;

import com.upc.coreentities.Security.Review;

public interface ReviewService {
    Review create(Long userId, Long businessId, Review review);
    Review delete(Long id);
    Review update(Long id, Review review);
}
