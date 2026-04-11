package com.playground.springbootblogapp.service;

import com.playground.springbootblogapp.domain.entity.Post;
import com.playground.springbootblogapp.domain.mapper.PostMapper;
import com.playground.springbootblogapp.domain.request.PostRequest;
import com.playground.springbootblogapp.exception.NotFoundException;
import com.playground.springbootblogapp.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public Page<Post> getPageablePosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Post getPost(UUID uuid) {
        return postRepository.findById(uuid).orElseThrow(NotFoundException::new);
    }

    public Post createPost(PostRequest request) {
        Post post = postMapper.toEntity(request);
        post.setCreated(LocalDateTime.now());

        return postRepository.save(post);
    }

    public Post editPost(UUID uuid, PostRequest request) {
        postRepository.findById(uuid).orElseThrow(NotFoundException::new);

        Post post = postMapper.toEntity(request);

        return postRepository.save(post);
    }

    public void deletePost(UUID uuid) {
        postRepository.findById(uuid).orElseThrow(NotFoundException::new);
        postRepository.deleteById(uuid);
    }
}
