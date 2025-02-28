package com.chuwa.learn.account_service.controller;

import com.chuwa.learn.account_service.dto.auth.request.LoginRequest;
import com.chuwa.learn.account_service.dto.auth.request.RegisterRequest;
import com.chuwa.learn.account_service.dto.auth.response.AuthResponse;
import com.chuwa.learn.account_service.dto.auth.response.LoginResponse;
import com.chuwa.learn.account_service.dto.auth.response.RegisterResponse;
import com.chuwa.learn.account_service.model.User;
import com.chuwa.learn.account_service.service.AuthService;
import com.chuwa.learn.account_service.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication API")
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    /**
     * User registration endpoint
     * Accepts RegisterRequest, returns RegisterResponse (with userId, email, token, etc.)
     */
    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Register a new user with email, username, and password")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        log.info("Registering new user with email: {}", request.getEmail());

        // Register user
        User newUser = authService.register(
                request.getEmail(),
                request.getUsername(),
                request.getPassword()
        );
        // Generate token
        String token = authService.login(
                request.getEmail(),
                request.getPassword()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new AuthResponse(token, newUser.getId(), newUser.getUsername())
        );
    }

    /**
     * User login endpoint
     * Accepts LoginRequest, returns LoginResponse (with token, userId, etc.)
     */
    @PostMapping("/login")
    @Operation(summary = "Authenticate user", description = "Authenticate a user with email/username and password")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        log.info("Login attempt for user: {}", request.getLoginName());

        String token = authService.login(request.getLoginName(), request.getPassword());

        return ResponseEntity.ok(AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .build());

    }

    /**
     * User logout endpoint
     * If you're using stateless JWT, you might simply rely on front-end to discard token
     * or maintain a blacklist in AuthService.
     */
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {
        // For stateless JWT, no server-side state to clear
        // If you keep session state, you can invalidate session here.
        Map<String, String> msg = new HashMap<>();
        msg.put("message", "User logged out successfully (stateless JWT).");
        return ResponseEntity.ok(msg);
    }
}


