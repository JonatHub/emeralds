package com.almaesmeralda.emeralds.controller;

import com.almaesmeralda.emeralds.dto.CreateUserRequest;
import com.almaesmeralda.emeralds.dto.UpdateUserRequest;
import com.almaesmeralda.emeralds.model.User;
import com.almaesmeralda.emeralds.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_create:user')")
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest request) {
        User user = userService.createUser(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).body(user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_write:users')")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {

        User updatedUser = userService.updateUser(id, request);
        return ResponseEntity.ok(updatedUser);
    }

}
