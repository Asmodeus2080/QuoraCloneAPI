package com.example.QuoraAPI.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentOnCommentResponseDto {
    private String id;
    private String userId;
    private String commentId;
    private String comment;
}
