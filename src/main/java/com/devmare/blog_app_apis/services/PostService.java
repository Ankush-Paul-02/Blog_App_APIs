package com.devmare.blog_app_apis.services;

import com.devmare.blog_app_apis.entities.Post;
import com.devmare.blog_app_apis.payloads.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);

    Post updatePost(PostDTO postDTO, Integer id);

    void deletePost(Integer id);

    List<PostDTO> getAllPosts();

    PostDTO getPostById(Integer id);

    List<PostDTO> getPostByCategory(Integer categoryId);

    List<PostDTO> getPostByUser(Integer userId);

    List<Post> searchPost(String keyword);

}
