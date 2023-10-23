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
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private Number quantity;
    private String image;
    private String state;
}
