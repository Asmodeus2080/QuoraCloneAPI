package com.example.QuoraAPI.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAnswerResponseDto {
    private String id;
    private String answer;
    private String userId;
    private String questionId;
}
