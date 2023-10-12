package com.upc.coreentities.Resource.Request;

import com.upc.coreentities.Resource.BusinessProfile.BusinessProfileDto;
import com.upc.coreentities.Resource.UserProfile.UserProfileDto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
    private Long id;
    private String category;
    private String estimatedBudget;
    private int area;
    private String location;
    private String file;
    private String description;
    private String status;
    private UserProfileDto userProfile;
    private BusinessProfileDto businessProfile;

    /*
    private Long id;
    private Date requestAt;
    private String status;
    private String description;
    private Boolean accepted;
    private Date acceptedAt;
    private Date updateAt;
    private UserProfileDto userProfile;
    private BusinessProfileDto businessProfile;

     */
}
