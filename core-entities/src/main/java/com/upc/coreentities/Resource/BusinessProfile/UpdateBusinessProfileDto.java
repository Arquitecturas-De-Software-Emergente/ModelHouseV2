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
public class UpdateBusinessProfileDto {
    private String name;
    private String description;
    private String image;
    private String address;
    private String webSite;
    private String phoneNumber;
}
