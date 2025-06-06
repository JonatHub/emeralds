package com.almaesmeralda.emeralds.service;

import com.almaesmeralda.emeralds.dto.LoginRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Value("${jwt.private-key}")
    private Resource privateKeyResource;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    // En un ambiente real, estos valores vendrían de una base de datos
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "admin123";

    public String generateToken(LoginRequest loginRequest) {
        // Validar credenciales
        if (!VALID_USERNAME.equals(loginRequest.getUsername()) || 
            !VALID_PASSWORD.equals(loginRequest.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        try {
            // Leer y preparar la clave privada
            String privateKeyPEM = new String(Files.readAllBytes(privateKeyResource.getFile().toPath()))
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

            byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encoded));

            // Crear claims (datos que irán en el token)
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", loginRequest.getUsername());
            claims.put("role", "ADMIN");
            claims.put("scope", "write:emeralds create:user write:users");

            // Generar el token con RS256
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(loginRequest.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                    .signWith(privateKey, SignatureAlgorithm.RS256)
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException("Error generating token", e);
        }
    }

    public boolean validateToken(String token) {
        // Aquí implementarías la validación del token
        // Podrías:
        // 1. Validar contra Auth0
        // 2. Validar la firma del JWT localmente
        // 3. Verificar scopes y permisos
        return true; // Implementar la validación real
    }
} 