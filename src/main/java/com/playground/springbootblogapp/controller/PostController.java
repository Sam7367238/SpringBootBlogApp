package com.playground.springbootblogapp.controller;

import com.playground.springbootblogapp.domain.dto.PostDto;
import com.playground.springbootblogapp.domain.entity.Post;
import com.playground.springbootblogapp.domain.mapper.PostMapper;
import com.playground.springbootblogapp.domain.request.PostRequest;
import com.playground.springbootblogapp.exception.NotFoundException;
import com.playground.springbootblogapp.service.PostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
class PostController {
    private final Logger logger = LoggerFactory.getLogger(PostController.class);

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
    public ResponseEntity<PostDto> createPost(
            UriComponentsBuilder uriBuilder,
            @Valid @RequestBody PostRequest request
    ) {
        Post post = postService.createPost(request);

        URI uri = uriBuilder.path("/posts/{uuid}").buildAndExpand(post.getUuid()).toUri();

        return ResponseEntity.created(uri).body(postMapper.toDto(post));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable UUID uuid,
            @Valid @RequestBody PostRequest request
       ) {
        Post post = postService.editPost(uuid, request);

        return ResponseEntity.ok(postMapper.toDto(post));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID uuid) {
        postService.deletePost(uuid);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Post not found."));
    }
}
