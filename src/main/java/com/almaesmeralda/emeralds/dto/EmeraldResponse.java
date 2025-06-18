package com.almaesmeralda.emeralds.dto;

import java.math.BigDecimal;
import java.util.List;

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
        List<String> imageUrls,
        BigDecimal lengthMm,
        BigDecimal widthMm
) {}
