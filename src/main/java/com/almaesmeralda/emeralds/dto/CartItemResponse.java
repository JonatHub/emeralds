package com.almaesmeralda.emeralds.dto;

import lombok.Data;

@Data
public class CartItemResponse {
    private Long id;
    private Long emeraldId;
    private String emeraldName;
    private int quantity;
}
