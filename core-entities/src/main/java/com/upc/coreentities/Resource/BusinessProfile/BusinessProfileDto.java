package com.upc.coreentities.Resource.BusinessProfile;

import com.upc.coreentities.Resource.AccountResource;
import com.upc.coreentities.Resource.Category.CategoryDto;
import com.upc.coreentities.Resource.Category.CreateCategoryDto;
import com.upc.coreentities.Resource.Review.ReviewDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class BusinessProfileDto {
    private Long id;
    private String name;
    private String description;
    private String image;
    private String address;
    private String webSite;
    private List<CategoryDto> categories;
    private String phoneNumber;
    private String foundationDate;
}
