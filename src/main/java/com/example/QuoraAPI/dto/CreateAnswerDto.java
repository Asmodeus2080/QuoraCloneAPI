package com.example.QuoraAPI.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAnswerDto {
    private String userId;
    private String text;
}
