package com.copartage.authservice.controller;

import com.copartage.authservice.model.User;
import com.copartage.authservice.service.UserService;
import com.copartage.authservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null && userService.checkPassword(user.getPassword(), existingUser.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(name = "Authorization") String token) {
        // Optionally: Invalidate the token in server-side (e.g., add to blacklist)
        return ResponseEntity.ok("Logout successful");
    }
}
