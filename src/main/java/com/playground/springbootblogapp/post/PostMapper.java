package com.playground.springbootblogapp.post;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(source = "created", target = "createdAt", dateFormat = "dd-MM-yyyy HH:mm:ss")
    PostDto toDto(Post post);

    @Mapping(target = "created", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    Post toEntity(PostRequest request);

    @Mapping(target = "created", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    void update(PostRequest request, @MappingTarget Post post);
}
