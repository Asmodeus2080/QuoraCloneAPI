package com.example.QuoraAPI.controllers;

import com.example.QuoraAPI.dto.AnswerCommentResponseDto;
import com.example.QuoraAPI.dto.CreateCommentDto;
import com.example.QuoraAPI.dto.CreateAnswerResponseDto;
import com.example.QuoraAPI.dto.UpdateAnswerDto;
import com.example.QuoraAPI.services.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/answers")
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PutMapping("{answerId}")
    public ResponseEntity<?> updateAnswer(@PathVariable String answerId, @RequestBody UpdateAnswerDto updateAnswerDetails) {
        try {
            return new ResponseEntity<CreateAnswerResponseDto>(answerService.updateAnswer(answerId, updateAnswerDetails), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("{answerId}/comments")
    public ResponseEntity<?> createAnswerComment(@PathVariable String answerId, @RequestBody CreateCommentDto commentDetails) {
        try {
            return new ResponseEntity<AnswerCommentResponseDto>(answerService.createAnswerComment(answerId, commentDetails), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
