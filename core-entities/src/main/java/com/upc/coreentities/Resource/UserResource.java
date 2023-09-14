package com.upc.coreentities.Resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {

    private Long id;
    private String emailAddress;
    private Boolean isActive;
    private String role;
    private String token;
}