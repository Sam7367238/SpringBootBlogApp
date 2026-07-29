package com.playground.springbootblogapp.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserDto createUser(RegisterUserRequest request) {
        User user = userMapper.toEntity(request);

        userRepository.save(user);

        return userMapper.toDto(user);
    }
}
