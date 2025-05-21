package com.almaesmeralda.emeralds.service;

import com.almaesmeralda.emeralds.dto.CreateEmeraldRequest;
import com.almaesmeralda.emeralds.model.Emerald;
import com.almaesmeralda.emeralds.repository.EmeraldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmeraldService {

    private final EmeraldRepository emeraldRepository;

    public Emerald createEmerald(CreateEmeraldRequest request) {
        Emerald emerald = new Emerald();
        emerald.setName(request.getName());
        emerald.setDescription(request.getDescription());
        emerald.setPrice(request.getPrice());
        emerald.setCaratWeight(request.getCaratWeight());
        emerald.setOrigin(request.getOrigin());
        emerald.setCertification(request.getCertification());
        emerald.setClarity(request.getClarity());
        emerald.setColor(request.getColor());
        emerald.setImageUrl(request.getImageUrl());
        emerald.setStockQuantity(request.getStockQuantity());
        emerald.setLengthMm(request.getLengthMm());
        emerald.setWidthMm(request.getWidthMm());

        return emeraldRepository.save(emerald);
    }
} 