package com.almaesmeralda.emeralds.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    // Podés permitir password opcional si no siempre se actualiza
    private String password;
}

