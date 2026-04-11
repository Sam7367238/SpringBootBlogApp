package com.playground.springbootblogapp.domain.request;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record PostRequest(
        @NotNull
        @Length(min = 3, max = 255)
        String title,

        @NotNull
        @Length(min = 3)
        String content
) {
}
