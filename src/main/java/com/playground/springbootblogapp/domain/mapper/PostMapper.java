package com.playground.springbootblogapp.domain.mapper;

import com.playground.springbootblogapp.domain.dto.PostDto;
import com.playground.springbootblogapp.domain.entity.Post;
import com.playground.springbootblogapp.domain.request.PostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(source = "created", target = "createdAt", dateFormat = "dd-MM-yyyy HH:mm:ss")
    PostDto toDto(Post post);

    @Mapping(target = "created", ignore = true)
    Post toEntity(PostRequest request);

    void update(PostRequest request, @MappingTarget Post post);
}
