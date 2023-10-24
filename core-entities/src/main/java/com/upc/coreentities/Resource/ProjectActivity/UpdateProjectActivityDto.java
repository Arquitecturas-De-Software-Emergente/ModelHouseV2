package com.upc.coreentities.Resource.ProjectActivity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProjectActivityDto {
    private String description;
    private Boolean isChecked;
}
