package com.upc.coreservice.Service.Interfaces;

import com.upc.coreentities.Security.Account;

public interface AccountService {
    Account findById(Long id);
    Account findByUserId(Long id);
    Account create(Long userId, Account account);
    //Account update(Long id, Account account);
}
