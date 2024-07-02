package com.example.QuoraAPI.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
public class QuestionDTO {
    private String id;
    private String topic;
    private String body;
    private String userId;
    private List<String> answer;
    private List<String> topicTags;

    // Getters and setters
    // Constructor
    public QuestionDTO(String id, String topic, String body, String userId, List<String> answer, List<String> topicTags) {
        this.id = id;
        this.topic = topic;
        this.body = body;
        this.userId = userId;
        this.answer = answer;
        this.topicTags = topicTags;
    }

}

