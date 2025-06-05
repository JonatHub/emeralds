package com.almaesmeralda.emeralds.repository;

import com.almaesmeralda.emeralds.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);
    Optional<CartItem> findByUserIdAndEmeraldId(Long userId, Long emeraldId);
}
