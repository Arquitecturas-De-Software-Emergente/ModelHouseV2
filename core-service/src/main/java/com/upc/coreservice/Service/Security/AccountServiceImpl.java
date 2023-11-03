package com.upc.coreservice.Service.Security;

import com.upc.coreentities.Security.Account;
import com.upc.coreentities.Security.AccountDetailsImpl;
import com.upc.coreentities.Util.Shared.exception.ResourceNotFoundException;
import com.upc.coreentities.Util.Shared.exception.ResourceValidationException;
import com.upc.coreservice.Repository.Security.AccountRepository;
import com.upc.coreservice.Service.Interfaces.AccountService;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    public UserDetails findEmailById(String idString){
        Long id = Long.parseLong(idString);
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null)
        {
            return null;
        }
        return AccountDetailsImpl.build(account);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmailAddress(email);
        return AccountDetailsImpl.build(account);
    }
}
