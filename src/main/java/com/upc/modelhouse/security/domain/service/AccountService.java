package com.upc.modelhouse.security.domain.service;

import com.upc.modelhouse.security.domain.model.entity.Account;

public interface AccountService {
    Account findById(Long id);
    Account findByUserId(Long id);
    Account create(Long userId, Account account);
    //Account update(Long id, Account account);
}
