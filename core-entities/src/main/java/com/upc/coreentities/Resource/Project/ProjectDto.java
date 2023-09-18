package com.upc.coreentities.Resource.Project;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private Long businessProfileId;
}
