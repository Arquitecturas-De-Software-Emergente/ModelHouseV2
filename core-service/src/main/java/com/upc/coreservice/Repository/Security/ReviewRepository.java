package com.upc.coreservice.Repository.Security;

import com.upc.coreentities.Security.Project;
import com.upc.coreentities.Security.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findReviewById(Long id);
    Review findReviewByBusinessProfileId(Long id);
}
