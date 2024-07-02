package com.example.QuoraAPI.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    private String username;
    private String email;
    private String bio;

    @Override
    public String toString() {
        return username+" "+email+" "+bio;
    }
}
