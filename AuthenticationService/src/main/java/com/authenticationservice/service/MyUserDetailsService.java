package com.authenticationservice.service;

import com.authenticationservice.entity.User;
import com.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User foundUser = user.get();
        return new org.springframework.security.core.userdetails.User(foundUser.getUsername(), foundUser.getPassword(), new ArrayList<>());
    }

    public String saveUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Error: Username already exists";
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Error: Email already exists";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }
}
