package com.example.QuoraAPI.services;

import com.example.QuoraAPI.dto.CreateUserDto;
import com.example.QuoraAPI.dto.CreateUserResponse;
import com.example.QuoraAPI.dto.UpdatedUserDto;
import com.example.QuoraAPI.models.User;
import com.example.QuoraAPI.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.FetchNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
    @Override
    public CreateUserResponse createUser(CreateUserDto userDetails) {
        User user = User.builder().
                email(userDetails.getEmail())
                .username(userDetails.getUsername())
                .bio(userDetails.getBio())
                .build();
        User result = userRepository.save(user);
        return  CreateUserResponse.builder()
                .username(result.getUsername())
                .email(result.getEmail())
                .Id(result.getId())
                .bio(result.getBio())
                .build();
    }

    @Override
    public CreateUserResponse getUser(String userId) {
        Optional<User> user;
        try {
            user = userRepository.findById(userId);
            if(user.isEmpty()) {
                throw new EntityNotFoundException("User with the "+ userId + " not found");
            }
        }
        catch (Exception e) {
//            e.printStackTrace();
            if(e.getClass() == EntityNotFoundException.class){
                throw new FetchNotFoundException("User with id " + userId + " not found", userId);
            }
            throw new FetchNotFoundException("Unable to fetch, PLease try again later!", userId);
        }
        return CreateUserResponse.builder()
                .username(user.get().getUsername())
                .bio(user.get().getBio())
                .Id(user.get().getId())
                .email(user.get().getEmail())
                .build();
    }

    @Override
    public CreateUserResponse updateUser(String userId, UpdatedUserDto updatedUserDetails) {
        System.out.println(updatedUserDetails);
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        if(updatedUserDetails.getBio() != null) {
            user.setBio(updatedUserDetails.getBio());
        }
        if(updatedUserDetails.getEmail() != null) {
            user.setEmail(updatedUserDetails.getEmail());
        }
        if(updatedUserDetails.getUsername() != null) {
            user.setUsername(updatedUserDetails.getUsername());
        }
        System.out.println(user);
        User res = userRepository.save(user);
        return CreateUserResponse.builder()
                .email(res.getEmail())
                .bio(res.getBio())
                .Id(res.getId())
                .username(res.getUsername())
                .build();
    }
}
