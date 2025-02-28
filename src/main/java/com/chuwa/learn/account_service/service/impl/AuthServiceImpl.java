package com.chuwa.learn.account_service.service.impl;

import com.chuwa.learn.account_service.model.User;
import com.chuwa.learn.account_service.repository.UserRepository;
import com.chuwa.learn.account_service.security.JwtTokenProvider;
import com.chuwa.learn.account_service.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    @Transactional
    public User register(String email, String username, String password) {
        log.info("Registering new user with email: {}", email);
        // Check if the email and username already exist
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already in use.");
        }
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already in use");
        }

        // Build a new User entity
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        user.setRoles(roles);

        // Save the user to database
        return userRepository.save(user);
    }

    @Override
    public String login(String loginName, String rawPassword) {
        log.info("Attempting login for user: {}", loginName);

        // 分别尝试通过用户名和邮箱查找
        User user = null;

        try {
            // 尝试通过用户名查找
            Optional<User> byUsername = userRepository.findByUsername(loginName);
            if (byUsername.isPresent()) {
                user = byUsername.get();
                log.info("Found user by username: {}", user.getUsername());
            } else {
                // 尝试通过邮箱查找
                Optional<User> byEmail = userRepository.findByEmail(loginName);
                if (byEmail.isPresent()) {
                    user = byEmail.get();
                    log.info("Found user by email: {}", user.getEmail());
                }
            }
        } catch (Exception e) {
            log.error("Error finding user: {}", e.getMessage(), e);
        }

        if (user == null) {
            log.error("User not found with login: {}", loginName);
            throw new BadCredentialsException("User not found with login: " + loginName);
        }

        // 验证密码
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            log.error("Invalid password for user: {}", user.getUsername());
            throw new BadCredentialsException("Invalid password");
        }

        // 生成JWT令牌
        return jwtTokenProvider.generateToken(user);
    }

}

