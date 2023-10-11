package com.devmare.blog_app_apis.controllers;

import com.devmare.blog_app_apis.payloads.ApiResponse;
import com.devmare.blog_app_apis.payloads.PostResponse;
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
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection
    ) {
        return new ResponseEntity<>(postService.getAllPosts(pageNumber, pageSize, sortBy, sortDirection), HttpStatus.OK);
    }

    //! http://localhost:8081/api/posts/{postId}
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {
        PostDTO postDTO = postService.getPostById(postId);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    //! http://localhost:8081/api/posts/{postId}
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePostById(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ApiResponse("Post is successfully deleted!", true);
    }

    //! http://localhost:8081/api/posts/{postId}
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePostById(@RequestBody PostDTO postDTO, @PathVariable Integer postId) {
        postService.updatePost(postDTO, postId);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    //! http://localhost:8081/api/posts/search/{keywords}
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDTO>> searchPostByTitle(
            @PathVariable String keywords
    ) {
        List<PostDTO> searchResult = postService.searchPost(keywords);
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }
}
