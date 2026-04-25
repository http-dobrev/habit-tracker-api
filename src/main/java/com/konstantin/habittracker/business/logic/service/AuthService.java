package com.konstantin.habittracker.business.logic.service;

import com.konstantin.habittracker.dto.request.RegisterRequest;
import com.konstantin.habittracker.dto.response.AuthResponse;
import com.konstantin.habittracker.exception.EmailAlreadyExistsException;
import com.konstantin.habittracker.exception.InvalidRequestException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public AuthResponse register(RegisterRequest request) {
        if (request == null) {
            throw new InvalidRequestException("Invalid request");
        }

        if (request.getEmail().equalsIgnoreCase("john@example.com")) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        return new AuthResponse(
                "jwt-token-here",
                3600,
                "User successfully registered"
        );
    }
}