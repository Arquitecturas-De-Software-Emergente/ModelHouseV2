package com.upc.coreentities.Resource.UserProfile;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
