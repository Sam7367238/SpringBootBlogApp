package com.playground.springbootblogapp.post;

import com.playground.springbootblogapp.user.User;
import com.playground.springbootblogapp.user.UserRepository;
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
    private final UserRepository userRepository;

    public Page<PostDto> getPageablePosts(Pageable pageable) {
        return postRepository.findAll(pageable).map(postMapper::toDto);
    }

    public PostDto getPost(UUID uuid) {
        Post post = postRepository.findById(uuid).orElseThrow(PostNotFoundException::new);

        return postMapper.toDto(post);
    }

    public PostDto createPost(CreatePostRequest request) {
        User user = userRepository.findById(request.userId()).orElseThrow();

        Post post = postMapper.toEntity(request);
        post.setCreated(LocalDateTime.now());
        post.setUser(user);

        postRepository.save(post);

        return postMapper.toDto(post);
    }

    public PostDto editPost(UUID uuid, UpdatePostRequest request) {
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
