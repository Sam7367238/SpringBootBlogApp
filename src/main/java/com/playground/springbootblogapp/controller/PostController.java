package com.playground.springbootblogapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RestController
@RequestMapping("/posts")
class PostController {
    @GetMapping
    public ResponseEntity<Map<String, String>> getAllPosts() {
        return ResponseEntity.ok(Map.of("message", "Posts"));
    }
}
