package com.playground.springbootblogapp.post;

import com.playground.springbootblogapp.authentication.AuthenticationService;
import com.playground.springbootblogapp.user.User;
import com.playground.springbootblogapp.user.UserRepository;
import com.playground.springbootblogapp.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final AuthenticationService authenticationService;

    public Page<PostDto> getPageablePosts(Pageable pageable) {
        return postRepository.findAll(pageable).map(postMapper::toDto);
    }

    public PostDto getPost(UUID uuid) {
        Post post = postRepository.findById(uuid).orElseThrow(PostNotFoundException::new);

        return postMapper.toDto(post);
    }

    public PostDto createPost(CreatePostRequest request) {
        User user = authenticationService.getCurrentUser();

        Post post = postMapper.toEntity(request);
        post.setCreated(LocalDateTime.now());
        post.setUser(user);

        postRepository.save(post);

        return postMapper.toDto(post);
    }

    public PostDto editPost(UUID uuid, UpdatePostRequest request) {
        Post post = postRepository.findById(uuid).orElseThrow(PostNotFoundException::new);
        User user = authenticationService.getCurrentUser();

        if (post.getUser() != user) {
            throw new AccessDeniedException("You can not edit other people's posts");
        }

        postMapper.update(request, post);

        postRepository.save(post);

        return postMapper.toDto(post);
    }

    public void deletePost(UUID uuid) {
        Post post = postRepository.findById(uuid).orElseThrow(PostNotFoundException::new);
        User user = authenticationService.getCurrentUser();

        if (post.getUser() != user) {
            throw new AccessDeniedException("You can not delete other people's posts");
        }

        postRepository.deleteById(uuid);
    }
}
