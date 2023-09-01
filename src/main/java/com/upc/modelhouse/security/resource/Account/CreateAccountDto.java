package com.upc.modelhouse.security.resource.Account;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountDto {
    @NotNull
    @NotBlank
    private Boolean isActive;
}
