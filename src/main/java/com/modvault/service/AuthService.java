package com.modvault.service;

import com.modvault.dto.AuthRequest;
import com.modvault.dto.AuthResponse;
import com.modvault.dto.RegisterRequest;
import com.modvault.entity.User;
import com.modvault.repository.UserRepository;
import com.modvault.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getFullName());
        return new AuthResponse(token, user.getId(), user.getEmail(), user.getFullName(), user.getAvatarLetter(), user.getRole());
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        String avatarLetter = request.getFullName() != null && !request.getFullName().isEmpty() 
                ? request.getFullName().substring(0, 1).toUpperCase() 
                : "U";

        User user = new User(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getFullName(),
                avatarLetter,
                "USER"
        );

        user = userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail(), user.getFullName());
        return new AuthResponse(token, user.getId(), user.getEmail(), user.getFullName(), user.getAvatarLetter(), user.getRole());
    }

    public User getGeethikaProfile() {
        return userRepository.findByEmail("geethika@gmail.com")
                .orElseGet(() -> userRepository.findAll().stream().findFirst().orElse(null));
    }
}
