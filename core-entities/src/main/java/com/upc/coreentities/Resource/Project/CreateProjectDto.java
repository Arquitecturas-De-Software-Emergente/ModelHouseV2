package com.upc.coreentities.Resource.Project;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectDto {
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private String image;
}
