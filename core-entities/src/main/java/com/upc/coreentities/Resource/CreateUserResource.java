package com.upc.coreentities.Resource;

import lombok.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResource {
    @NotBlank
    @NotNull
    @Email
    private String emailAddress;
    @NotNull
    @NotBlank
    private String password;
}