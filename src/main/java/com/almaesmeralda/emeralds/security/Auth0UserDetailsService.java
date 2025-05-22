package com.almaesmeralda.emeralds.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class Auth0UserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Para Auth0, realmente no necesitamos cargar usuarios por username
        // ya que la autenticación se maneja a través de tokens JWT
        // Pero Spring Security requiere esta implementación
        return new User(
            username,
            "", // contraseña vacía ya que usamos tokens
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
} 