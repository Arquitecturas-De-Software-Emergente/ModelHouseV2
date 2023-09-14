package com.upc.coreentities.Resource.ProjectResource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectResourceDto {
    private String description;
    private Number quantity;
    private String state;
}
