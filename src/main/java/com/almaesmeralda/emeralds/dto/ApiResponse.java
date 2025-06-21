package com.almaesmeralda.emeralds.dto;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class ApiResponse {
    private boolean success;
    private String message;
    private ZonedDateTime timestamp;
}
