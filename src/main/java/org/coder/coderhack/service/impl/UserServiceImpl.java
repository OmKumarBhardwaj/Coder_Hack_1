package org.coder.coderhack.service.impl;

import lombok.AllArgsConstructor;
import org.coder.coderhack.dto.UserRegistrationDto;
import org.coder.coderhack.entity.User;
import org.coder.coderhack.exception.UserAlreadyExistsException;
import org.coder.coderhack.repository.UserRepository;
import org.coder.coderhack.service.UserService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) {
        Optional<User> optionalUser = userRepository.findById(userRegistrationDto.getUserId());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("User already registered with given user id : " + userRegistrationDto.getUserId());
        }

        userRepository.save(registerNewUser(userRegistrationDto));
    }

    private User registerNewUser(UserRegistrationDto userRegistrationDto) {
        return User.builder()
                .userId(userRegistrationDto.getUserId())
                .userName(userRegistrationDto.getUserName())
                .score(0)
                .badges(new LinkedHashSet<>())
                .build();
    }



}