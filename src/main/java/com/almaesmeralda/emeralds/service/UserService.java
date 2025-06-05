package com.almaesmeralda.emeralds.service;

import com.almaesmeralda.emeralds.dto.CreateUserRequest;
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
        // setear otros campos
        return userRepository.save(user);
    }
}

