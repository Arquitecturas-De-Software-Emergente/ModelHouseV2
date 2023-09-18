package com.upc.coreentities.Resource.UserProfile;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserProfileDto {
    @NotNull
    @NotBlank
    private String firstName;
    private String image;
    @NotNull
    @NotBlank
    private String lastName;
    @NotNull
    @NotBlank
    private String gender;
    @NotNull
    @NotBlank
    private String phoneNumber;
}
