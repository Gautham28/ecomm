package com.gautham.ecomm.auth.controller;

import com.gautham.ecomm.auth.dto.AuthResponse;
import com.gautham.ecomm.auth.dto.LoginRequest;
import com.gautham.ecomm.auth.dto.RegisterRequest;
import com.gautham.ecomm.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}