package com.playground.springbootblogapp.authentication;

public record LoginResponse(
        Jwt accessToken,
        Jwt refreshToken
) {
}
