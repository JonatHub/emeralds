package com.almaesmeralda.emeralds.service;

import com.almaesmeralda.emeralds.dto.CreateUserRequest;
import com.almaesmeralda.emeralds.dto.UpdateUserRequest;
import com.almaesmeralda.emeralds.model.User;
import com.almaesmeralda.emeralds.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{

    // Inyectá repositorios o dependencias necesarias
    private final UserRepository userRepository;

    public User createUser(CreateUserRequest request) {
        // lógica para crear usuario, por ejemplo:
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole("USER");
        return userRepository.save(user);
    }
    public User updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(request.getPassword()); // Encriptar si es necesario
        }

        return userRepository.save(user);
    }
}

