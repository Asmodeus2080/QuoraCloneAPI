package com.example.QuoraAPI.services;

import com.example.QuoraAPI.dto.AnswerCommentResponseDto;
import com.example.QuoraAPI.dto.CommentOnCommentResponseDto;
import com.example.QuoraAPI.dto.CreateAnswerResponseDto;
import com.example.QuoraAPI.dto.CreateCommentDto;
import com.example.QuoraAPI.models.Answer;
import com.example.QuoraAPI.models.Comment;
import com.example.QuoraAPI.models.User;
import com.example.QuoraAPI.repositories.CommentRepository;
import com.example.QuoraAPI.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.FetchNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImp implements CommentService{

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    public CommentServiceImp(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }
    @Override
    public CommentOnCommentResponseDto createCommentForComment(String commentId, CreateCommentDto commentDetails) {
        try {
            Optional<Comment> optionalComment = commentRepository.findById(commentId);
            if(optionalComment.isEmpty()) {
                throw new EntityNotFoundException("Comment not found");
            }
            Comment parentComment = optionalComment.get();
            Optional<User> optionalUser = userRepository.findById(commentDetails.getUserId());
            if(optionalUser.isEmpty()) {
                throw new EntityNotFoundException("User not found");
            }
            User user = optionalUser.get();
            Comment comment = Comment.builder()
                    .parentComment(parentComment)
                    .user(user)
                    .comment(commentDetails.getText())
                    .build();
            Comment response = commentRepository.save(comment);
            user.getComments().add(response);
            userRepository.save(user);
            return CommentOnCommentResponseDto.builder()
                    .id(response.getId())
                    .comment(commentId)
                    .userId(response.getUser().getId())
                    .comment(response.getComment())
                    .build();
        }
        catch (Exception e) {
            if(e.getClass() == EntityNotFoundException.class){
                throw new FetchNotFoundException(e.getMessage(), commentId);
            }
            throw new FetchNotFoundException("Unable to fetch, PLease try again later!", commentId);
        }
    }
}
