package com.upc.coreservice.Service.Security;

import com.upc.coreentities.Security.Account;
import com.upc.coreentities.Util.Shared.exception.ResourceNotFoundException;
import com.upc.coreentities.Util.Shared.exception.ResourceValidationException;
import com.upc.coreservice.Repository.Security.AccountRepository;
import com.upc.coreservice.Repository.Security.UserRepository;
import com.upc.coreservice.Service.Interfaces.AccountService;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final Validator validator;
    private static final String ENTITY = "Account";

    @Override
    public Account findById(Long id) {
        return accountRepository.findAccountById(id);
    }

    @Override
    public Account findByUserId(Long userId) {

        return accountRepository.findAccountByUserId(userId);
    }

    @Override
    public Account create(Long userId, Account account) {
        Set<ConstraintViolation<Account>> violations = validator.validate(account);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        Account accountExist = accountRepository.findAccountByUserId(userId);
        if(accountExist != null)
            throw new ResourceNotFoundException("Account is exist");
        return userRepository.findById(userId).map(user -> {
            account.setUser(user);
            return accountRepository.save(account);
        }).orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }
}
