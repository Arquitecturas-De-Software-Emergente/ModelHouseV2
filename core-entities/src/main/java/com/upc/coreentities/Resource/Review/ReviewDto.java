package com.upc.coreentities.Resource.Review;

import com.upc.coreentities.Resource.BusinessProfile.BusinessProfileDto;
import com.upc.coreentities.Resource.Project.ProjectDto;
import com.upc.coreentities.Resource.UserProfile.UserProfileDto;
import com.upc.coreentities.Security.Project;
import com.upc.coreentities.Security.UserProfile;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private Float score;
    private String comment;
    private Date reviewDate;
    private String projectId;
    private UserProfileDto userProfileId;
}
