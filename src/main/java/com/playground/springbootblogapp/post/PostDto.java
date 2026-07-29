package com.playground.springbootblogapp.post;

import java.util.UUID;

public record PostDto(
        UUID uuid,
        String title,
        String content,
        String createdAt,
        Long userId
) {
}
