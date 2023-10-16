package com.devmare.blog_app_apis.controllers;

import com.devmare.blog_app_apis.configuration.AppConstants;
import com.devmare.blog_app_apis.payloads.ApiResponse;
import com.devmare.blog_app_apis.payloads.PostResponse;
import com.devmare.blog_app_apis.payloads.dto.PostDTO;
import com.devmare.blog_app_apis.services.FileService;
import com.devmare.blog_app_apis.services.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
@Tag(name = "Post Controller", description = "This contains Post related API methods")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

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
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortDirection
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

    //! http://localhost:8081/api/posts/image/upload/{id}
    @PostMapping("/posts/image/upload/{id}")
    public ResponseEntity<PostDTO> uploadPostImage(
            @RequestParam MultipartFile image,
            @PathVariable Integer id
    ) throws IOException {
        PostDTO postDTO = postService.getPostById(id);
        String filename = fileService.uploadImage(path, image);
        postDTO.setImageName(filename);
        PostDTO updatedPost = postService.updatePost(postDTO, id);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    //! http://localhost:8081/api/posts/image/{imageName}
    @GetMapping(value = "posts/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resourceStream = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resourceStream, response.getOutputStream());
    }
}
