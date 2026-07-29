package com.playground.springbootblogapp.post;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record UpdatePostRequest(
        @NotNull(message = "Please pass in the title")
        @Length(min = 3, max = 255, message = "The title can only be between 3 and 255 characters long")
        String title,

        @NotNull(message = "Please pass in the content")
        @Length(min = 3, message = "The content must be a minimum of 3 characters long")
        String content
) {
}
