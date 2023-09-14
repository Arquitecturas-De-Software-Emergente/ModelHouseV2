package com.upc.coreentities.Resource;

import lombok.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialsResource {
    @NotNull
    @NotBlank
    @Email
    private String emailAddress;
    @NotNull
    @NotBlank
    @Size(min = 8, max = 16)
    private String password;
}
