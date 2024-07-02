package com.example.QuoraAPI.controllers;

import com.example.QuoraAPI.dto.AnswerCommentResponseDto;
import com.example.QuoraAPI.dto.CommentOnCommentResponseDto;
import com.example.QuoraAPI.dto.CreateCommentDto;
import com.example.QuoraAPI.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comments")
public class CommentsController {

    private final CommentService commentService;
    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("{commentId}/comments")
    public ResponseEntity<?> createCommentForComment(@PathVariable String commentId, @RequestBody CreateCommentDto commentDetails) {
        try {
            return new ResponseEntity<CommentOnCommentResponseDto>(commentService.createCommentForComment(commentId, commentDetails), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
