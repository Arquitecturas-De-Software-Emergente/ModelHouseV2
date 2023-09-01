package com.upc.modelhouse.security.api;

import com.upc.modelhouse.security.domain.model.entity.Account;
import com.upc.modelhouse.security.domain.model.entity.User;
import com.upc.modelhouse.security.domain.persistence.UserRepository;
import com.upc.modelhouse.security.domain.service.AccountService;
import com.upc.modelhouse.security.domain.service.UserService;
import com.upc.modelhouse.security.mapping.AccountMapper;
import com.upc.modelhouse.security.resource.Account.CreateAccountDto;
import com.upc.modelhouse.security.resource.UserCredentialsResource;
import com.upc.modelhouse.security.resource.ResponseErrorResource;
import com.upc.modelhouse.security.resource.UserResource;
import com.upc.modelhouse.security.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService authService;
    private final AccountService accountService;
    private final AccountMapper mapper;

    @Autowired
    private JwtUtil jwtUtil;

    private AuthenticationManager manager;
    @Autowired
    private UserRepository userRepository;
    private static final String statusBody = "User already exists";

    @PostMapping("/login")
    @Operation(summary = "Login", tags = {"User"})
    public ResponseEntity<?> login(@Valid @RequestBody UserCredentialsResource user) {
        ResponseErrorResource errorResource = new ResponseErrorResource();
        errorResource.setMessage(statusBody);
        UserResource response = authService.login(user);
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
    @Operation(summary = "Register", tags = {"User"})
    public ResponseEntity<?> register(@Valid @RequestBody UserCredentialsResource credentials) {

        ResponseErrorResource errorResource = new ResponseErrorResource();
        errorResource.setMessage(statusBody);

        if(userRepository.findByEmailAddress(credentials.getEmailAddress()) != null) {
            return ResponseEntity.badRequest().body(errorResource);
        }
        User user = authService.register(credentials);
        CreateAccountDto account = new CreateAccountDto();
        account.setIsActive(true);
        if(user!=null){
            accountService.create(user.getId(), mapper.toModel(account));
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.ok("Registration failed, please try again");
    }
}
