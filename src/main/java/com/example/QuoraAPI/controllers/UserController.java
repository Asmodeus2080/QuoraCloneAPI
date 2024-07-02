package com.example.QuoraAPI.controllers;

import com.example.QuoraAPI.dto.CreateUserDto;
import com.example.QuoraAPI.dto.CreateUserResponse;
import com.example.QuoraAPI.dto.UpdatedUserDto;
import com.example.QuoraAPI.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    private ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserDto userDetails) {
        return new ResponseEntity<>(userService.createUser(userDetails), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    private ResponseEntity<?>  getUser(@PathVariable String userId) {
        try {
            return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}")
    private ResponseEntity<?> updateUserDetails(@RequestBody UpdatedUserDto updatedUserDetails, @PathVariable String userId) {
        try {
            return new ResponseEntity<>(userService.updateUser(userId, updatedUserDetails), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
