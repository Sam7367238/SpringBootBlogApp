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

    public Page<Post> getPageablePosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Post getPost(UUID uuid) {
        return postRepository.findById(uuid).orElseThrow(PostNotFoundException::new);
    }

    public Post createPost(PostRequest request) {
        Post post = postMapper.toEntity(request);
        post.setCreated(LocalDateTime.now());

        return postRepository.save(post);
    }

    public Post editPost(UUID uuid, PostRequest request) {
        Post post = postRepository.findById(uuid).orElseThrow(PostNotFoundException::new);

        postMapper.update(request, post);

        return postRepository.save(post);
    }

    public void deletePost(UUID uuid) {
        postRepository.findById(uuid).orElseThrow(PostNotFoundException::new);
        postRepository.deleteById(uuid);
    }
}
