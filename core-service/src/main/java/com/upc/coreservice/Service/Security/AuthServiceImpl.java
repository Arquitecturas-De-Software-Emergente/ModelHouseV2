package com.upc.coreservice.Service.Security;

import com.upc.coreentities.Resource.AccountCredentialsResource;
import com.upc.coreentities.Resource.AccountResource;
import com.upc.coreentities.Security.Account;
import com.upc.coreservice.Mapping.AccountMapper;
import com.upc.coreservice.Repository.Security.AccountRepository;
import com.upc.coreservice.Service.Interfaces.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder encoder;
    private AccountMapper mapper;

    public AccountResource login(AccountCredentialsResource credentials) {
        Account account = accountRepository.findByEmailAddress(credentials.getEmailAddress());
        if (account == null)
            throw new IllegalArgumentException("User not found");
        if (!encoder.matches(credentials.getPassword(), account.getPassword()))
            throw new IllegalArgumentException("Wrong password");

        return mapper.toResource(account);
    }

    @Override
    public Account register(AccountCredentialsResource credentialsResource) {
        Account registeredAccount = new Account();
        registeredAccount.setEmailAddress(credentialsResource.getEmailAddress());
        registeredAccount.setPassword(encoder.encode(credentialsResource.getPassword()));
        registeredAccount.setIsActive(true);

        registeredAccount = accountRepository.save(registeredAccount);

        return registeredAccount;
    }
}
