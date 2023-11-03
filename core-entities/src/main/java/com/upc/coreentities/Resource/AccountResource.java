package com.upc.coreentities.Resource;

import com.upc.coreentities.Security.BusinessProfile;
import com.upc.coreentities.Security.UserProfile;
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
    private String deviceId;
    private String token;
    private String userProfileId;
    private String businessProfileId;
}
