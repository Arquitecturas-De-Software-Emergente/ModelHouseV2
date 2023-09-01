package com.upc.modelhouse.security.resource.Account;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private Boolean isActive;
}
