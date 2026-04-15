package com.Spendless.Product.service.userService;

import com.Spendless.Product.exception.UserNotFoundException;
import com.Spendless.Product.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email){
     return userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User doesn't exist"));
    }

}
