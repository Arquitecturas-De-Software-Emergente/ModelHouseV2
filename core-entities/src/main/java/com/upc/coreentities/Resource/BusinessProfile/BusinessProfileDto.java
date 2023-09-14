package com.upc.coreentities.Resource.BusinessProfile;

import com.upc.coreentities.Resource.Account.AccountDto;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class BusinessProfileDto {
    private Long id;
    private String name;
    private String description;
    private String image;
    private String address;
    private String webSite;
    private String phoneBusiness;
    private String foundationDate;
    private AccountDto account;
}
