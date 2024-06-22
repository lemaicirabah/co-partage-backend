package com.copartage.authservice.service;

import com.copartage.authservice.model.User;
import com.copartage.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) throws Exception {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new Exception("Username already exists");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }

    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
