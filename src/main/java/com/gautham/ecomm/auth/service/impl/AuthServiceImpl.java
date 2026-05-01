package com.gautham.ecomm.auth.service.impl;

import com.gautham.ecomm.auth.dto.AuthResponse;
import com.gautham.ecomm.auth.dto.LoginRequest;
import com.gautham.ecomm.auth.dto.RegisterRequest;
import com.gautham.ecomm.auth.service.AuthService;
import com.gautham.ecomm.exception.EmailAlreadyExistsException;
import com.gautham.ecomm.security.JwtService;
import com.gautham.ecomm.user.entity.Role;
import com.gautham.ecomm.user.entity.User;
import com.gautham.ecomm.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String register(RegisterRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.SELLER)
                .createdAt(LocalDateTime.now())
                .build();
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matches) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}