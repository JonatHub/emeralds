package com.almaesmeralda.emeralds.repository;

import com.almaesmeralda.emeralds.model.Emerald;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmeraldRepository extends JpaRepository<Emerald, Long> {
    List<Emerald> findByOrigin(String origin);
    List<Emerald> findByPriceLessThanEqual(Double price);
    List<Emerald> findByCaratWeightBetween(Double minWeight, Double maxWeight);
} 