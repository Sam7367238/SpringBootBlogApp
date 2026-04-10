package com.playground.springbootblogapp.domain.dto;

import java.time.Instant;
import java.util.UUID;

public record PostDto(
        UUID id,
        String title,
        String content,
        Instant createdAt
) {
}
