package org.coder.coderhack.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.coder.coderhack.constant.StatusCode;
import org.coder.coderhack.dto.ResponseDto;
import org.coder.coderhack.dto.UserRegistrationDto;
import org.coder.coderhack.dto.UserScoreUpdateDto;
import org.coder.coderhack.entity.User;
import org.coder.coderhack.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class UserController {
    private UserService userservice;

    @PostMapping
    public ResponseEntity<ResponseDto> registerUser(@Valid @RequestBody UserRegistrationDto user) {
        userservice.registerUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(StatusCode.STATUS_201, StatusCode.STATUS_201));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ResponseDto> updateUserScore(@PathVariable String userId, @Valid @RequestBody UserScoreUpdateDto userScoreUpdateDto) {
        boolean isUpdated = userservice.updateUserScore(userId, userScoreUpdateDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(StatusCode.STATUS_200, StatusCode.STATUS_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(StatusCode.STATUS_417, StatusCode.MESSAGE_417_Update));
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userservice.getAllUsers();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = userservice.getUserById(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseDto> deregisterUser(@PathVariable String userId) {
        boolean isDeleted = userservice.deleteUserById(userId);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(StatusCode.STATUS_200, StatusCode.STATUS_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(StatusCode.STATUS_417, StatusCode.MESSAGE_417_DELETE));
        }
    }

}
