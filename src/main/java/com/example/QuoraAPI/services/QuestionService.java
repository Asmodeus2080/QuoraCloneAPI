package com.example.QuoraAPI.services;

import com.example.QuoraAPI.dto.*;

import java.util.List;

public interface QuestionService {

    CreateQuestionResponseDto createQuestion(CreateQuestionDto questionInfo);

    List<QuestionDTO> searchQuestions(String topic);

    CreateAnswerResponseDto createAnswer(String questionId, CreateAnswerDto answerDetails);
}
