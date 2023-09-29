package com.devmare.blog_app_apis.services.impl;

import com.devmare.blog_app_apis.configuration.ModelMapperConfiguration;
import com.devmare.blog_app_apis.entities.Post;
import com.devmare.blog_app_apis.payloads.PostDTO;
import com.devmare.blog_app_apis.repositories.CategoryRepository;
import com.devmare.blog_app_apis.repositories.PostRepository;
import com.devmare.blog_app_apis.repositories.UserRepository;
import com.devmare.blog_app_apis.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.util.*;

@Service
public class PostServiceImple implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapperConfiguration modelMapperConfiguration;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Post createPost(PostDTO postDTO, Integer usreId, Integer categoryId) {
        Post post = modelMapperConfiguration.modelMapper().map(postDTO, Post.class);
        post.setImageName("default.png");
        post.setCreatedAt(new Date());
        return null;
    }

    @Override
    public Post updatePost(PostDTO postDTO, Integer id) {
        return null;
    }

    @Override
    public void deletePost(Integer id) {

    }

    @Override
    public List<Post> getAllPosts() {
        return null;
    }

    @Override
    public Post getPostById(Integer id) {
        return null;
    }

    @Override
    public List<Post> getPostByCategory(String categoryId) {
        return null;
    }

    @Override
    public List<Post> getPostByUser(String userId) {
        return null;
    }

    @Override
    public List<Post> searchPost(String keyword) {
        return null;
    }
}
