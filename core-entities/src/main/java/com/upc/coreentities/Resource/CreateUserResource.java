package com.upc.coreentities.Resource;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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