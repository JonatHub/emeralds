package com.almaesmeralda.emeralds.controller;

import com.almaesmeralda.emeralds.dto.CartItemResponse;
import com.almaesmeralda.emeralds.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartItemResponse>> getCart(@RequestParam Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PostMapping
    public ResponseEntity<CartItemResponse> addToCart(@RequestParam Long userId,
                                              @RequestParam Long emeraldId,
                                              @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.addToCart(userId, emeraldId, quantity));
    }

    @DeleteMapping
    public ResponseEntity<Void> removeFromCart(@RequestParam Long userId,
                                               @RequestParam Long emeraldId) {
        cartService.removeFromCart(userId, emeraldId);
        return ResponseEntity.noContent().build();
    }
}
