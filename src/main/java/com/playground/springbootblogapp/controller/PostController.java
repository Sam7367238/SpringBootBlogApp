package com.playground.springbootblogapp.controller;

import com.playground.springbootblogapp.domain.dto.PostDto;
import com.playground.springbootblogapp.domain.entity.Post;
import com.playground.springbootblogapp.domain.mapper.PostMapper;
import com.playground.springbootblogapp.domain.request.CreatePostRequest;
import com.playground.springbootblogapp.exception.NotFoundException;
import com.playground.springbootblogapp.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
class PostController {
    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping
    public Page<PostDto> getAllPosts(Pageable pageable) {
        Page<Post> posts =  postService.getPageablePosts(pageable);

        return posts.map(postMapper::toDto);
    }

    @GetMapping("/{uuid}")
    public PostDto getPost(@PathVariable UUID uuid) {
        return postMapper.toDto(postService.getPost(uuid));
    }

    @PostMapping
    public PostDto createPost(@RequestBody CreatePostRequest request) {

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Post not found."));
    }
}
