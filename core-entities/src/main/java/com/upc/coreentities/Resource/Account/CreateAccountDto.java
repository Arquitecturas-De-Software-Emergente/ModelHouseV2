package com.upc.coreentities.Resource.Account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
