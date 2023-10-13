package com.upc.coreservice.Mapping;

import com.upc.coreentities.Resource.Review.CreateReview;
import com.upc.coreentities.Resource.Review.ReviewDto;
import com.upc.coreentities.Security.Review;
import com.upc.coreentities.Util.Shared.mapping.EnhancedModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@AllArgsConstructor
public class ReviewMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;
    public ReviewDto toResource(Review model){
        return mapper.map(model, ReviewDto.class);
    }
    public Review toModel(ReviewDto resource) {
        return mapper.map(resource, Review.class);
    }

    public Review toModel(CreateReview resource) {
        return mapper.map(resource, Review.class);
    }
}
