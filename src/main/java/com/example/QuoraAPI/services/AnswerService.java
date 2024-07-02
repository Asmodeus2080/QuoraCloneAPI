package com.example.QuoraAPI.services;

import com.example.QuoraAPI.dto.AnswerCommentResponseDto;
import com.example.QuoraAPI.dto.CreateCommentDto;
import com.example.QuoraAPI.dto.CreateAnswerResponseDto;
import com.example.QuoraAPI.dto.UpdateAnswerDto;

public interface AnswerService {
    CreateAnswerResponseDto updateAnswer(String answerId, UpdateAnswerDto answerDetail);

    AnswerCommentResponseDto createAnswerComment(String answerId, CreateCommentDto commentDetails);
}
