package com.hrms.hrms_backend.controller;

import com.hrms.hrms_backend.dto.ApiResponse;
import com.hrms.hrms_backend.dto.AuthResponse;
import com.hrms.hrms_backend.dto.LoginRequest;
import com.hrms.hrms_backend.dto.RegisterRequest;
import com.hrms.hrms_backend.entity.User;
import com.hrms.hrms_backend.repository.UserRepository;
import com.hrms.hrms_backend.security.JwtUtil;
import com.hrms.hrms_backend.service.AuthService;
import com.hrms.hrms_backend.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final EmployeeService employeeService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(
            AuthService authService,
            EmployeeService employeeService,
            JwtUtil jwtUtil,
            UserRepository userRepository
    ) {
        this.authService = authService;
        this.employeeService = employeeService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }


    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }


    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody String refreshToken) {

        String username = jwtUtil.extractUsername(refreshToken);

        User user = userRepository
                .findByUsername(username)
                .orElseThrow();

        String newAccessToken = jwtUtil.generateToken(
                user.getUsername(),
                user.getRole()
        );

        return new AuthResponse(newAccessToken, refreshToken);
    }
}