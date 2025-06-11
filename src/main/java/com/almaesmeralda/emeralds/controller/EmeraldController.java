package com.almaesmeralda.emeralds.controller;

import com.almaesmeralda.emeralds.dto.CreateEmeraldRequest;
import com.almaesmeralda.emeralds.dto.EmeraldResponse;
import com.almaesmeralda.emeralds.model.Emerald;
import com.almaesmeralda.emeralds.service.EmeraldService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/emeralds")
@RequiredArgsConstructor
public class EmeraldController {

    private final EmeraldService emeraldService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_write:emeralds')")
    public ResponseEntity<Emerald> createEmerald(@Valid @RequestBody CreateEmeraldRequest request) {
        Emerald emerald = emeraldService.createEmerald(request);
        
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(emerald.getId())
            .toUri();

        return ResponseEntity.created(location).body(emerald);
    }

    @GetMapping
    public ResponseEntity<List<EmeraldResponse>> getAllEmeralds() {
        List<EmeraldResponse> emeralds = emeraldService.getAllEmeralds();
        return ResponseEntity.ok(emeralds);
    }
} 