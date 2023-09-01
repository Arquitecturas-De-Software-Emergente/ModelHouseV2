package com.upc.modelhouse.security.service;

import com.upc.modelhouse.security.domain.model.entity.Account;
import com.upc.modelhouse.security.domain.persistence.AccountRepository;
import com.upc.modelhouse.security.domain.persistence.UserRepository;
import com.upc.modelhouse.security.domain.service.AccountService;
import com.upc.modelhouse.shared.exception.ResourceNotFoundException;
import com.upc.modelhouse.shared.exception.ResourceValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
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
