package com.upc.coreentities.Resource.ProjectResource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProjectResourceDto {
    private String description;
    private Number quantity;
    private String state;
}
