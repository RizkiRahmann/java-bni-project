package com.bni.bni.service;

import com.bni.bni.entity.User;
import com.bni.bni.repository.UserRepository;
import com.bni.bni.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(String username, String password, String email) {
        if (repo.existsByUsername(username)) {
            return "User already exists";
        }

        OffsetDateTime now = OffsetDateTime.now();

        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(encoder.encode(password));
        user.setRole("USER");
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        user.setIsActive(true);
        user.setEmailAddress(email); // Bisa null, karena kolom ini NULLABLE

        repo.save(user);
        return "Registered successfully";
    }

    public String login(String username, String password) {
        Optional<User> userOpt = repo.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getIsActive() && encoder.matches(password, user.getPasswordHash())) {
                return jwtUtil.generateToken(user.getUsername(), user.getRole());
            }
        }
        return null;
    }

    public Optional<User> getUserByUsername(String username) {
        return repo.findByUsername(username);
    }
}