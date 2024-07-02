package com.example.QuoraAPI.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerCommentResponseDto {
    private String id;
    private String userId;
    private String answerId;
    private String comment;
}
