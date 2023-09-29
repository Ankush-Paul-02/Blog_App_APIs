package com.devmare.blog_app_apis.services;

import com.devmare.blog_app_apis.entities.Post;
import com.devmare.blog_app_apis.payloads.PostDTO;

import java.util.List;

public interface PostService {

    Post createPost(PostDTO postDTO, Integer userId, Integer categoryId);

    Post updatePost(PostDTO postDTO, Integer id);

    void deletePost(Integer id);

    List<Post> getAllPosts();

    Post getPostById(Integer id);

    List<Post> getPostByCategory(String categoryId);

    List<Post> getPostByUser(String userId);

    List<Post> searchPost(String keyword);

}
