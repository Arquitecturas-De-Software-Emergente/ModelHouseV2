package com.upc.coreentities.Resource.BusinessProfile;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
    private String image;
    private String address;
    private String webSite;
    @NotNull
    @NotBlank
    private String phoneBusiness;

}
