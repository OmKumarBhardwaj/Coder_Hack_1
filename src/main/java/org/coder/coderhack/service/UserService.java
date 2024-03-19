package org.coder.coderhack.service;

import org.coder.coderhack.dto.UserRegistrationDto;

public interface UserService {
    void registerUser(UserRegistrationDto userRegistrationDto);
}
