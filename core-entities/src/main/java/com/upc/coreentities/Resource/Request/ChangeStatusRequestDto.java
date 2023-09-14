package com.upc.coreentities.Resource.Request;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ChangeStatusRequestDto {
    @NotNull
    @NotBlank
    private String status;
}
