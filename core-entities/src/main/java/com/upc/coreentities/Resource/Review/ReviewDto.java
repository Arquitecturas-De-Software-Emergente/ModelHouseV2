package com.upc.coreentities.Resource.Review;

import com.upc.coreentities.Resource.BusinessProfile.BusinessProfileDto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private int score;
    private String comment;
    private Date reviewDate;
    private BusinessProfileDto businessProfile;
}
