package com.example.demo.dto;

import java.time.Instant;

public record ResponseDto(
        Long id,
        Instant date,
        String data
) {
}
