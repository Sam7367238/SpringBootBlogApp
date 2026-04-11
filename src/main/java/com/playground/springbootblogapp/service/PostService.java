package com.playground.springbootblogapp.service;

import com.playground.springbootblogapp.domain.entity.Post;
import com.playground.springbootblogapp.domain.mapper.PostMapper;
import com.playground.springbootblogapp.domain.request.CreatePostRequest;
import com.playground.springbootblogapp.exception.NotFoundException;
import com.playground.springbootblogapp.repository.PostRepository;
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

    public Page<Post> getPageablePosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Post getPost(UUID uuid) {
        Post post = postRepository.findById(uuid).orElse(null);

        if (post == null) {
            throw new NotFoundException();
        }

        return post;
    }

    public Post createPost(CreatePostRequest request) {
        // Builder can be used here but I decided not to.
        Post post = new Post();
        post.setTitle(request.title());
        post.setContent(request.content());
        post.setCreated(LocalDateTime.now());

        postRepository.save(post);
    }
}
