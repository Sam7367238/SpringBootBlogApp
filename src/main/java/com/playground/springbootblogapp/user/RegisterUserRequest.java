package com.playground.springbootblogapp.user;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record RegisterUserRequest(
        @Length(min = 3, max = 20, message = "The username must only be between 3 and 20 characters long")
        @NotNull(message = "Username cannot be empty")
        String username,

        @Length(min = 3, max = 20, message = "The password must only be between 3 and 20 characters long")
        @NotNull(message = "Password cannot be empty")
        String password
) {
}
