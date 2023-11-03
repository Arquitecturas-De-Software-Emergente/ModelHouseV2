package com.upc.coreservice.Repository.Security;

import com.upc.coreentities.Security.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmailAddress(String emailAddress);
    Account findAccountById(Long id);
}
