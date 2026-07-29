package com.playground.springbootblogapp.post;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public Page<PostDto> getPageablePosts(Pageable pageable) {
        return postRepository.findAll(pageable).map(postMapper::toDto);
    }

    public PostDto getPost(UUID uuid) {
        Post post = postRepository.findById(uuid).orElseThrow(PostNotFoundException::new);

        return postMapper.toDto(post);
    }

    public PostDto createPost(PostRequest request) {
        Post post = postMapper.toEntity(request);
        post.setCreated(LocalDateTime.now());

        postRepository.save(post);

        return postMapper.toDto(post);
    }

    public PostDto editPost(UUID uuid, PostRequest request) {
        Post post = postRepository.findById(uuid).orElseThrow(PostNotFoundException::new);

        postMapper.update(request, post);

        postRepository.save(post);

        return postMapper.toDto(post);
    }

    public void deletePost(UUID uuid) {
        postRepository.findById(uuid).orElseThrow(PostNotFoundException::new);
        postRepository.deleteById(uuid);
    }
}
