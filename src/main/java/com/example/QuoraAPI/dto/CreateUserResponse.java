package com.example.QuoraAPI.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {

    private String Id;
    private String username;
    private String email;
    private String bio;

}
