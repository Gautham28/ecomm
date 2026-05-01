package com.gautham.ecomm.auth.service;

import com.gautham.ecomm.auth.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);
}