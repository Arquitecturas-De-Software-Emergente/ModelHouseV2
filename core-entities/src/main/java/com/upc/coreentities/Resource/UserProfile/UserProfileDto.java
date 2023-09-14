package com.upc.coreentities.Resource.UserProfile;

import com.upc.coreentities.Resource.UserResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
    private Long id;
    private String firstName;
    private String image;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private Date registrationDate;
    private Date lastLogin;
    private UserResource user;
}
