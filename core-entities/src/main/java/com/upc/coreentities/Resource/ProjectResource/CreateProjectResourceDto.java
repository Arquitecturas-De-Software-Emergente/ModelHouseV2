package com.upc.coreentities.Resource.ProjectResource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectResourceDto {

    private String description;
    private Number quantity;
    private String resourceType;
    private String state;
    private String image;
}
