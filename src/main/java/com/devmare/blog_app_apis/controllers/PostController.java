package com.devmare.blog_app_apis.controllers;

import com.devmare.blog_app_apis.payloads.dto.PostDTO;
import com.devmare.blog_app_apis.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    //! http://localhost:8081/api/users/{userId}/category/{categoryId}/posts
    @PostMapping("/users/{userId}/categories/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId) {
        PostDTO createdPost = postService.createPost(postDTO, userId, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    //! http://localhost:8081/api/users/{userId}/posts
    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId) {
        List<PostDTO> posts = postService.getPostByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    //! http://localhost:8081/api/categories/{categoryId}/posts
    @GetMapping("/categories/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer categoryId) {
        List<PostDTO> posts = postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    //! http://localhost:8081/api/posts
    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    //! http://localhost:8081/api/posts/{postId}
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {
        PostDTO postDTO = postService.getPostById(postId);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }
}
