package com.almaesmeralda.emeralds.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "emeralds")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emerald {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @Positive
    private Double caratWeight;

    @NotNull
    private String origin;

    private String certification;

    @NotNull
    private String clarity;

    @NotNull
    private String color;

    @Column(name = "image_url")
    private String imageUrl;

    @NotNull
    @Positive
    private Integer stockQuantity;

    // Nuevos campos añadidos en V2
    @Column(name = "length_mm", precision = 5, scale = 2)
    private BigDecimal lengthMm;

    @Column(name = "width_mm", precision = 5, scale = 2)
    private BigDecimal widthMm;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 