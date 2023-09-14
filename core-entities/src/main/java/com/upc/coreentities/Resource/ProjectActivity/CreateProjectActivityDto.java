package com.upc.coreentities.Resource.ProjectActivity;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectActivityDto {
    private String status;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String description;
}
