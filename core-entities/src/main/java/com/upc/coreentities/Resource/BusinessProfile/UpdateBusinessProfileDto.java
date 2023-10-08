package com.upc.coreentities.Resource.BusinessProfile;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBusinessProfileDto {
    private String name;
    private String description;
    private MultipartFile image;
    private String address;
    private String webSite;
    private String phoneBusiness;
    private String foundationDate;
}
