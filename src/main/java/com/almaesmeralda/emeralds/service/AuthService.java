package com.almaesmeralda.emeralds.service;

import com.almaesmeralda.emeralds.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Service
public class AuthService {

    @Value("${auth0.domain}")
    private String auth0Domain;

    @Value("${auth0.clientId}")
    private String clientId;

    @Value("${auth0.clientSecret}")
    private String clientSecret;

    @Value("${auth0.audience}")
    private String audience;

    private final RestTemplate restTemplate = new RestTemplate();

    public String generateToken(LoginRequest loginRequest) {
        // Validar credenciales (esto podría cambiarse según tus necesidades)
        if (!"admin".equals(loginRequest.getUsername()) || 
            !"admin123".equals(loginRequest.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        // Preparar la solicitud a Auth0
        String tokenUrl = String.format("https://%s/oauth/token", auth0Domain);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("audience", audience);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        // Hacer la solicitud a Auth0
        ResponseEntity<Map> response = restTemplate.exchange(
            tokenUrl,
            HttpMethod.POST,
            request,
            Map.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return (String) response.getBody().get("access_token");
        } else {
            throw new BadCredentialsException("Failed to obtain token from Auth0");
        }
    }

    public boolean validateToken(String token) {
        // La validación ahora se maneja a través de Spring Security
        // usando las claves públicas de Auth0
        return true;
    }
} 