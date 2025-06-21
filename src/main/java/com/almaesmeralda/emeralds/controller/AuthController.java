package com.almaesmeralda.emeralds.controller;

import com.almaesmeralda.emeralds.dto.ApiResponse;
import com.almaesmeralda.emeralds.dto.LoginRequest;
import com.almaesmeralda.emeralds.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.generateToken(loginRequest);
        return ResponseEntity.ok(Map.of(
            "access_token", token,
            "token_type", "Bearer",
            "expires_in", "86400"
        ));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword(@Valid @RequestBody com.almaesmeralda.auth.dto.ForgotPasswordRequest request) {
        ApiResponse response = authService.processForgotPassword(request);
        return ResponseEntity.ok(response);
    }

} 