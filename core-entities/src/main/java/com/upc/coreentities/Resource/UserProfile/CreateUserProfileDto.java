package com.upc.coreentities.Resource.UserProfile;

import com.upc.coreentities.Resource.AccountResource;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserProfileDto {
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String address;
    private String reviewId;
}
