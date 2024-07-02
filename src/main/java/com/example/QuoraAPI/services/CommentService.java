package com.example.QuoraAPI.services;

import com.example.QuoraAPI.dto.CommentOnCommentResponseDto;
import com.example.QuoraAPI.dto.CreateCommentDto;

public interface CommentService {
    CommentOnCommentResponseDto createCommentForComment(String commentId, CreateCommentDto commentDetails);
}
