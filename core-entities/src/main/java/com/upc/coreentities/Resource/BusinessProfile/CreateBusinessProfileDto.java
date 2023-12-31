package com.upc.coreentities.Resource.BusinessProfile;

import com.upc.coreentities.Resource.Category.CreateCategoryDto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateBusinessProfileDto {
    private String name;
    private String description;
    private String image;
    private String address;
    private String webSite;
    private String phoneNumber;
}
