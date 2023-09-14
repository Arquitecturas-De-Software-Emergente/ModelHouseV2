package com.upc.coreservice.Service.Security;

import com.upc.coreentities.Security.User;
import com.upc.coreentities.Security.UserDetailsImpl;
import com.upc.coreservice.Repository.Security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails findEmailById(String idString){
        Long id = Long.parseLong(idString);
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
        {
            return null;
        }
        return UserDetailsImpl.build(user);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAddress(email);
        return UserDetailsImpl.build(user);
    }
}