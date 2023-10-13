package com.upc.security.controller;

import com.upc.coreentities.Resource.AccountCredentialsResource;
import com.upc.coreentities.Resource.AccountResource;
import com.upc.coreentities.Resource.ResponseErrorResource;
import com.upc.coreentities.Security.Account;
import com.upc.coreservice.Mapping.AccountMapper;
import com.upc.coreservice.Repository.Security.AccountRepository;
import com.upc.coreservice.Service.Interfaces.AccountService;
import com.upc.coreservice.Util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("")
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper mapper;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private AccountRepository accountRepository;
    private static final String statusBody = "User already exists";

    public AccountController(AccountService accountService, AccountMapper mapper) {
        this.accountService = accountService;
        this.mapper = mapper;
    }

    @PostMapping("/login")
    @Operation(summary = "Login", tags = {"Account"})
    public ResponseEntity<?> login(@Valid @RequestBody AccountCredentialsResource user) {
        ResponseErrorResource errorResource = new ResponseErrorResource();
        errorResource.setMessage(statusBody);
        AccountResource response = accountService.login(user);
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmailAddress(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);
        if(response == null) {
            return ResponseEntity.badRequest().body(errorResource);
        }
        response.setToken(jwt);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    @Operation(summary = "Register", tags = {"Account"})
    public ResponseEntity<?> register(@Valid @RequestBody AccountCredentialsResource credentials) {

        ResponseErrorResource errorResource = new ResponseErrorResource();
        errorResource.setMessage(statusBody);

        if(accountRepository.findByEmailAddress(credentials.getEmailAddress()) != null) {
            return ResponseEntity.badRequest().body(errorResource);
        }
        Account account = accountService.register(credentials);
        return ResponseEntity.ok(Objects.requireNonNullElse(account, "Registration failed, please try again"));
    }
}
