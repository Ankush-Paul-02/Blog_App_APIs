package com.devmare.blog_app_apis.services.impl;

import com.devmare.blog_app_apis.entities.Comment;
import com.devmare.blog_app_apis.entities.Post;
import com.devmare.blog_app_apis.exceptions.ResourceNotFoundException;
import com.devmare.blog_app_apis.payloads.dto.CommentDTO;
import com.devmare.blog_app_apis.repositories.CommentRepository;
import com.devmare.blog_app_apis.repositories.PostRepository;
import com.devmare.blog_app_apis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post ", "id: ", postId));
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        comment.setPost(post);
        Comment createdComment = commentRepository.save(comment);
        return modelMapper.map(createdComment, CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Commment ", "id: ", commentId));
        commentRepository.delete(comment);
    }
}
