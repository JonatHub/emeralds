package com.almaesmeralda.emeralds.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDenied(AccessDeniedException ex) {
        ex.printStackTrace(); // Para que al menos salga en consola
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                Map.of("error", "No tienes permisos para acceder a este recurso.")
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleBadCredentials(BadCredentialsException ex) {
        ex.printStackTrace(); // Para que al menos salga en consola
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                Map.of("error", "Credenciales inválidas.")
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuthentication(AuthenticationException ex) {
        ex.printStackTrace(); // Para que al menos salga en consola
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                Map.of("error", "No autenticado.")
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneral(Exception ex) {
        ex.printStackTrace(); // Para que al menos salga en consola
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Map.of("error", "Error interno del servidor.")
        );
    }
}

