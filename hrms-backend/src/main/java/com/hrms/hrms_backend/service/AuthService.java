package com.hrms.hrms_backend.service;

import com.hrms.hrms_backend.dto.AuthResponse;
import com.hrms.hrms_backend.dto.LoginRequest;
import com.hrms.hrms_backend.dto.RegisterRequest;
import com.hrms.hrms_backend.entity.User;
import com.hrms.hrms_backend.repository.UserRepository;
import com.hrms.hrms_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthService {


    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository,
            JwtUtil jwtUtil,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ manual password check
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String accessToken = jwtUtil.generateToken(
                user.getUsername(),
                user.getRole()
        );

        String refreshToken = jwtUtil.generateRefreshToken(
                user.getUsername()
        );

        return new AuthResponse(accessToken, refreshToken);
    }
    public String register(RegisterRequest request) {

        // check user exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();

        user.setUsername(request.getUsername());

        // 🔐 encode password here (IMPORTANT)
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // default role if not provided
        String role = request.getRole() != null
                ? request.getRole()
                : "ROLE_USER";

        // ensure ROLE_ prefix
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        user.setRole(role);

        userRepository.save(user);

        return "User registered successfully";
    }
}