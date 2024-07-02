package com.example.QuoraAPI.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateQuestionDto {
    private String userId;
    private String title;
    private String body;
    private List<String> topics = new ArrayList<>();

    @Override
    public String toString() {
        return userId + " " + title + " " + body + " " + topics;
    }
}
