package com.devmare.blog_app_apis.services;

import com.devmare.blog_app_apis.payloads.dto.CommentDTO;

public interface CommentService {

    CommentDTO createComment(CommentDTO commentDTO, Integer postId);

    void deleteComment(Integer commentId);
}
