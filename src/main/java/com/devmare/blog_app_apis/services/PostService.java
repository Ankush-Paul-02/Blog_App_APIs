package com.devmare.blog_app_apis.services;

import com.devmare.blog_app_apis.payloads.PostResponse;
import com.devmare.blog_app_apis.payloads.dto.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);

    PostDTO updatePost(PostDTO postDTO, Integer id);

    void deletePost(Integer id);

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);

    PostDTO getPostById(Integer id);

    List<PostDTO> getPostByCategory(Integer categoryId);

    List<PostDTO> getPostByUser(Integer userId);

    List<PostDTO> searchPost(String keyword);

}
