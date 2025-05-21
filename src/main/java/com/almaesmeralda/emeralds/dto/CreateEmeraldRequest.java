package com.almaesmeralda.emeralds.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateEmeraldRequest {
    @NotNull(message = "El nombre es requerido")
    private String name;

    @NotNull(message = "La descripción es requerida")
    private String description;

    @NotNull(message = "El precio es requerido")
    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal price;

    @NotNull(message = "El peso en quilates es requerido")
    @Positive(message = "El peso debe ser mayor a 0")
    private Double caratWeight;

    @NotNull(message = "El origen es requerido")
    private String origin;

    private String certification;

    @NotNull(message = "La claridad es requerida")
    private String clarity;

    @NotNull(message = "El color es requerido")
    private String color;

    private String imageUrl;

    @NotNull(message = "La cantidad en stock es requerida")
    @Positive(message = "La cantidad debe ser mayor a 0")
    private Integer stockQuantity;

    @Positive(message = "El largo debe ser mayor a 0")
    private BigDecimal lengthMm;

    @Positive(message = "El ancho debe ser mayor a 0")
    private BigDecimal widthMm;
} 