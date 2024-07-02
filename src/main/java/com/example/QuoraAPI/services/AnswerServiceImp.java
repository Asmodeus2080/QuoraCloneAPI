package com.example.QuoraAPI.services;

import com.example.QuoraAPI.dto.*;
import com.example.QuoraAPI.models.*;
import com.example.QuoraAPI.repositories.AnswerRepository;
import com.example.QuoraAPI.repositories.CommentRepository;
import com.example.QuoraAPI.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.FetchNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerServiceImp implements AnswerService{

    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    private final CommentRepository commentRepository;
    public AnswerServiceImp(AnswerRepository answerRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }
    @Override
    public CreateAnswerResponseDto updateAnswer(String answerId, UpdateAnswerDto answerDetail) {
        try {
            Optional<Answer> optinalAnswer = answerRepository.findById(answerId);
            if(optinalAnswer.isEmpty()) {
                throw new EntityNotFoundException("Question not found");
            }
            Answer answer = optinalAnswer.get();
            answer.setAnswer(answerDetail.getText());
            Answer response = answerRepository.save(answer);
            return  CreateAnswerResponseDto.builder()
                    .userId(response.getUser().getId())
                    .id(response.getId())
                    .answer(response.getAnswer())
                    .questionId(answer.getQuestionId().getId())
                    .build();

        }
        catch (Exception e) {
            if(e.getClass() == EntityNotFoundException.class){
                throw new FetchNotFoundException(e.getMessage(), answerId);
            }
            throw new FetchNotFoundException("Unable to fetch, PLease try again later!", answerId);
        }
    }

    @Override
    public AnswerCommentResponseDto createAnswerComment(String answerId, CreateCommentDto commentDetails) {
        try {
           Optional<User> optionalUser = userRepository.findById(commentDetails.getUserId());
            if(optionalUser.isEmpty()) {
                throw new EntityNotFoundException("User not found");
            }
            User user = optionalUser.get();
            Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
            if(optionalAnswer.isEmpty()) {
                throw new EntityNotFoundException("Answer not found");
            }
            Answer answer = optionalAnswer.get();
            Comment comment = Comment.builder()
                    .answer(answer)
                    .user(user)
                    .comment(commentDetails.getText())
                    .build();
            Comment response = commentRepository.save(comment);
            user.getComments().add(response);
            answer.getComments().add(response);
            return AnswerCommentResponseDto.builder()
                    .id(response.getId())
                    .answerId(response.getAnswer().getId())
                    .userId(response.getUser().getId())
                    .comment(response.getComment())
                    .build();

        }
        catch (Exception e) {
            if(e.getClass() == EntityNotFoundException.class){
                throw new FetchNotFoundException(e.getMessage(), answerId);
            }
            throw new FetchNotFoundException("Unable to fetch, PLease try again later!", answerId);
        }
    }
}
