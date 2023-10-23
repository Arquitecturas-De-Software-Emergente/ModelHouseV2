package com.upc.coreservice.Mapping;

import com.upc.coreentities.Resource.Review.CreateReviewDto;
import com.upc.coreentities.Resource.Review.ReviewDto;
import com.upc.coreentities.Security.Review;
import com.upc.coreentities.Util.Shared.mapping.EnhancedModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@AllArgsConstructor
public class ReviewMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;
    public List<ReviewDto> listToResource(List<Review> model){
        return mapper.mapList(model, ReviewDto.class);
    }
    public ReviewDto toResource(Review model){
        return mapper.map(model, ReviewDto.class);
    }
    public Review toModel(ReviewDto resource) {
        return mapper.map(resource, Review.class);
    }

    public Review toModel(CreateReviewDto resource) {
        return mapper.map(resource, Review.class);
    }
}
