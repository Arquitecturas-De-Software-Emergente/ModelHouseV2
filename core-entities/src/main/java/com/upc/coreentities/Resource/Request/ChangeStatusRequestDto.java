package com.upc.coreentities.Resource.Request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
