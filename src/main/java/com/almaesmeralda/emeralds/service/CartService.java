package com.almaesmeralda.emeralds.service;

import com.almaesmeralda.emeralds.dto.CartItemResponse;
import com.almaesmeralda.emeralds.model.CartItem;
import com.almaesmeralda.emeralds.repository.CartItemRepository;
import com.almaesmeralda.emeralds.repository.EmeraldRepository;
import com.almaesmeralda.emeralds.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final EmeraldRepository emeraldRepository;


    public List<CartItemResponse> getCart(Long userId) {
        return cartItemRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CartItemResponse addToCart(Long userId, Long emeraldId, int quantity) {
        CartItem item = cartItemRepository.findByUserIdAndEmeraldId(userId, emeraldId)
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setUser(userRepository.getReferenceById(userId));
                    newItem.setEmerald(emeraldRepository.getReferenceById(emeraldId));
                    newItem.setQuantity(0);
                    return newItem;
                });

        item.setQuantity(item.getQuantity() + quantity);
        return mapToResponse(cartItemRepository.save(item));
    }

    private CartItemResponse mapToResponse(CartItem item) {
        CartItemResponse response = new CartItemResponse();
        response.setId(item.getId());
        response.setEmeraldId(item.getEmerald().getId());
        response.setEmeraldName(item.getEmerald().getName());
        response.setQuantity(item.getQuantity());
        return response;
    }

    public void removeFromCart(Long userId, Long emeraldId) {
        cartItemRepository.findByUserIdAndEmeraldId(userId, emeraldId)
                .ifPresent(cartItemRepository::delete);
    }
}
