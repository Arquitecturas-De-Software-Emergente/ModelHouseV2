package com.upc.modelhouse.ServiceManagement.resource.ProjectActivity;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProjectActivityDto {
    private String status;
    private String name;
    private String description;
}
