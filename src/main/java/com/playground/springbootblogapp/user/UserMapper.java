package com.playground.springbootblogapp.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "posts", ignore = true)
    User toEntity(RegisterUserRequest request);

    UserDto toDto(User user);
}
