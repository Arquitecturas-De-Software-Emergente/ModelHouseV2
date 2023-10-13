package com.upc.coreservice.Service.Interfaces;

import com.upc.coreentities.Resource.AccountCredentialsResource;
import com.upc.coreentities.Resource.AccountResource;
import com.upc.coreentities.Security.Account;

public interface AccountService {
    AccountResource login (AccountCredentialsResource credentials);
    Account register(AccountCredentialsResource credentialsResource);
}
