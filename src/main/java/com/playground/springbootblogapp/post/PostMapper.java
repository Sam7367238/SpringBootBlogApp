package com.playground.springbootblogapp.post;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(source = "created", target = "createdAt", dateFormat = "dd-MM-yyyy HH:mm:ss")
    @Mapping(target = "userId", source = "user.id")
    PostDto toDto(Post post);

    @Mapping(target = "created", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "user", ignore = true)
    Post toEntity(CreatePostRequest request);

    @Mapping(target = "created", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "user", ignore = true)
    void update(UpdatePostRequest request, @MappingTarget Post post);
}
