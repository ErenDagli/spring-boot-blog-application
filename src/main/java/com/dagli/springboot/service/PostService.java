package com.dagli.springboot.service;

import com.dagli.springboot.dto.PostDto;
import com.dagli.springboot.dto.PostResponse;
import com.dagli.springboot.entity.Post;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy,String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);

    List<PostDto> getPostsByCategory(long categoryId);
}
