package com.almaesmeralda.emeralds.dto;

import java.math.BigDecimal;

public record EmeraldResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Double caratWeight,
        String origin,
        String certification,
        String clarity,
        String color,
        String imageUrl,
        BigDecimal lengthMm,
        BigDecimal widthMm
) {}
