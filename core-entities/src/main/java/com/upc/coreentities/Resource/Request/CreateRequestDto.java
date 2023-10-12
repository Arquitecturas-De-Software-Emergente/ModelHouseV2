package com.upc.coreentities.Resource.Request;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateRequestDto {
    private String category;
    private String estimatedBudget;
    private int area;
    private String location;
    private String file;
    private String description;
    private String status;

    /*
    private String status;
    private String description;
    private Boolean accepted;

     */
}
