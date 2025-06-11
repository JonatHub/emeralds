package com.almaesmeralda.emeralds.service;

import com.almaesmeralda.emeralds.dto.CreateEmeraldRequest;
import com.almaesmeralda.emeralds.dto.EmeraldResponse;
import com.almaesmeralda.emeralds.model.Emerald;
import com.almaesmeralda.emeralds.repository.EmeraldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public List<EmeraldResponse> getAllEmeralds() {
        return emeraldRepository.findAll().stream()
                .map(emerald -> new EmeraldResponse(
                        emerald.getId(),
                        emerald.getName(),
                        emerald.getDescription(),
                        emerald.getPrice(),
                        emerald.getCaratWeight(),
                        emerald.getOrigin(),
                        emerald.getCertification(),
                        emerald.getClarity(),
                        emerald.getColor(),
                        emerald.getImageUrl(),
                        emerald.getLengthMm(),
                        emerald.getWidthMm()
                ))
                .collect(Collectors.toList());
    }

    public List<Emerald> getEmeraldsByParams(Long id, String name, String origin, String clarity) {
        Specification<Emerald> spec = Specification.where(null);
        if (id != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("id"), id));
        }
        if (name != null) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }

        if (origin != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(cb.lower(root.get("origin")), origin.toLowerCase()));
        }

        if (clarity != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(cb.lower(root.get("clarity")), clarity.toLowerCase()));
        }

        List<Emerald> emeralds = emeraldRepository.findAll(spec);
        return emeralds;
    }



} 