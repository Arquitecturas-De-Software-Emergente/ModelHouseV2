package com.upc.coreentities.Resource.Review;

import com.upc.coreentities.Resource.BusinessProfile.BusinessProfileDto;
import com.upc.coreentities.Resource.Project.ProjectDto;
import com.upc.coreentities.Security.Project;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Float score;
    private String comment;
    private Date reviewDate;
    private ProjectDto project;
}
