package com.devmare.blog_app_apis.controllers;

import com.devmare.blog_app_apis.payloads.AppApiResponse;
import com.devmare.blog_app_apis.payloads.dto.CommentDTO;
import com.devmare.blog_app_apis.services.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "scheme1")
@RestController
@RequestMapping("/api/")
@Tag(name = "Comment Controller", description = "This contains Comment related API methods")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //! http://localhost:8081/api/posts/{postId}/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(
            @RequestBody CommentDTO commentDTO,
            @PathVariable Integer postId
    ) {
        CommentDTO createdComment = commentService.createComment(commentDTO, postId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    //! http://localhost:8081/api/comments/{commentId}
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<AppApiResponse> deleteComment(
            @PathVariable Integer commentId
    ) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new AppApiResponse("Comment deleted successfully!", true), HttpStatus.OK);
    }
}
