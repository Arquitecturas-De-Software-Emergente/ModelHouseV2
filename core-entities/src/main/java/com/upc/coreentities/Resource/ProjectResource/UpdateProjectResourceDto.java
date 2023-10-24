package com.upc.coreentities.Resource.ProjectResource;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProjectResourceDto {
    private String description;
    private Number quantity;
    private Boolean isChecked;
}
