package com.upc.coreentities.Resource.UserProfile;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserProfileDto {
    private String firstName;
    private String image;
    private String lastName;
    private String phoneNumber;
    private String address;
}
