package com.upc.coreservice.Service.Interfaces;

import com.upc.coreentities.Resource.AccountCredentialsResource;
import com.upc.coreentities.Resource.AccountResource;
import com.upc.coreentities.Security.Account;

public interface AccountService {
    AccountResource login (AccountCredentialsResource credentials);
    Account register(AccountCredentialsResource credentialsResource);

    /*
    Account findById(Long id);
    Account findByUserId(Long id);
    Account create(Long userId, Account account);
    //Account update(Long id, Account account);

     */
}
