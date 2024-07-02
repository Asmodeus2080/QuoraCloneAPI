package com.example.QuoraAPI.controllers;


import com.example.QuoraAPI.dto.CreateAnswerDto;
import com.example.QuoraAPI.dto.CreateAnswerResponseDto;
import com.example.QuoraAPI.dto.CreateQuestionDto;
import com.example.QuoraAPI.dto.QuestionDTO;
import com.example.QuoraAPI.models.Question;
import com.example.QuoraAPI.services.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    @PostMapping
    public ResponseEntity<?> createQuestion(@RequestBody CreateQuestionDto questionInfo) {
        try {
            return new ResponseEntity<>(questionService.createQuestion(questionInfo), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/search")
    public ResponseEntity<?> searchQuestions(@RequestParam(required = false) String topic)  {
        try {
            return new ResponseEntity<List<QuestionDTO>>(questionService.searchQuestions(topic), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("{questionId}/answers")
    public ResponseEntity<?> createAnswer(@PathVariable String questionId, @RequestBody CreateAnswerDto answerDetails) {
        try {
            return new ResponseEntity<CreateAnswerResponseDto>(questionService.createAnswer(questionId, answerDetails), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
