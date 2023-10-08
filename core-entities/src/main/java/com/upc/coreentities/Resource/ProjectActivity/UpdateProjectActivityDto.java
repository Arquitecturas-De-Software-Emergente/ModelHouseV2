package com.upc.coreentities.Resource.ProjectActivity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProjectActivityDto {
    private String status;
    private MultipartFile image;
    private String name;
    private String description;
}
