package com.playground.springbootblogapp.post;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    private final PostService postService;

    @GetMapping
    public Page<PostDto> getAllPosts(Pageable pageable) {
        return postService.getPageablePosts(pageable);
    }

    @GetMapping("/{uuid}")
    public PostDto getPost(@PathVariable UUID uuid) {
        return postService.getPost(uuid);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(
            UriComponentsBuilder uriBuilder,
            @Valid @RequestBody PostRequest request
    ) {
        PostDto postDto = postService.createPost(request);

        URI uri = uriBuilder.path("/posts/{uuid}").buildAndExpand(postDto.uuid()).toUri();

        return ResponseEntity.created(uri).body(postDto);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable UUID uuid,
            @Valid @RequestBody PostRequest request
       ) {
        PostDto postDto = postService.editPost(uuid, request);

        return ResponseEntity.ok(postDto);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID uuid) {
        postService.deletePost(uuid);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Post not found."));
    }
}
