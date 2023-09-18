package com.upc.coreentities.Resource.ProjectActivity;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
