package com.upc.modelhouse.security.service;

import com.upc.modelhouse.security.domain.model.entity.User;
import com.upc.modelhouse.security.domain.persistence.UserRepository;
import com.upc.modelhouse.security.domain.service.UserService;
import com.upc.modelhouse.security.mapping.UserMapper;
import com.upc.modelhouse.security.resource.UserCredentialsResource;
import com.upc.modelhouse.security.resource.UserResource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    private UserMapper mapper;

    public UserResource login(UserCredentialsResource credentials) {
        User account = userRepository.findByEmailAddress(credentials.getEmailAddress());
        if (account == null)
            throw new IllegalArgumentException("User not found");
        if (!encoder.matches(credentials.getPassword(), account.getPassword()))
            throw new IllegalArgumentException("Wrong password");

        return mapper.toResource(account);
    }

    @Override
    public User register(UserCredentialsResource credentialsResource) {
        User registeredAccount = new User();
        registeredAccount.setEmailAddress(credentialsResource.getEmailAddress());
        registeredAccount.setPassword(encoder.encode(credentialsResource.getPassword()));
        registeredAccount.setIsActive(true);
        registeredAccount.setRole("user");
        registeredAccount = userRepository.save(registeredAccount);

        return registeredAccount;
    }
}
