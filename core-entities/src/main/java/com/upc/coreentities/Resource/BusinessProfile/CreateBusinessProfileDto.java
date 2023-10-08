package com.upc.coreentities.Resource.BusinessProfile;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateBusinessProfileDto {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    @Size(max = 1000)
    private String description;
    private String address;
    private String webSite;
    private MultipartFile image;
    @NotNull
    @NotBlank
    private String phoneBusiness;

}
