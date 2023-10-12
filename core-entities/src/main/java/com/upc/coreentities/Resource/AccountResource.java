package com.upc.coreentities.Resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class AccountResource {
    private Long id;
    private String emailAddress;
    private Boolean isActive;
    private String role;
    private String token;
}
