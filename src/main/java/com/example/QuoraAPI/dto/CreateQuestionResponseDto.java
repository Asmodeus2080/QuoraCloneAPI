package com.example.QuoraAPI.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateQuestionResponseDto {
    private String userId;
    private String title;
    private String body;
    private List<String> topics = new ArrayList<>();
}
