package com.upc.coreentities.Resource.Request;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateRequestDto {
    private String status;
    private String description;
    private Boolean accepted;
}
