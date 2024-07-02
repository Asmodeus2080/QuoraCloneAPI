package com.example.QuoraAPI.services;

import com.example.QuoraAPI.dto.CreateUserDto;
import com.example.QuoraAPI.dto.CreateUserResponse;
import com.example.QuoraAPI.dto.UpdatedUserDto;
public interface UserService {
    CreateUserResponse createUser(CreateUserDto user);

    CreateUserResponse getUser(String userId);

    CreateUserResponse updateUser(String userId, UpdatedUserDto updatedDetails);
}
