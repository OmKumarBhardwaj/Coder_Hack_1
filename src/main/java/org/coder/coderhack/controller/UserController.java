package org.coder.coderhack.controller;


import lombok.AllArgsConstructor;
import org.coder.coderhack.constant.StatusCode;
import org.coder.coderhack.dto.ResponseDto;
import org.coder.coderhack.dto.UserRegistrationDto;
import org.coder.coderhack.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userservice;

    @PostMapping
    public ResponseEntity<ResponseDto> registerUser(@RequestBody UserRegistrationDto user) {
        userservice.registerUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(StatusCode.STATUS_201, StatusCode.STATUS_201));
    }

}
