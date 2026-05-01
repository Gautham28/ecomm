package com.gautham.ecomm.auth.service;

import com.gautham.ecomm.auth.dto.AuthResponse;
import com.gautham.ecomm.auth.dto.LoginRequest;
import com.gautham.ecomm.auth.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}