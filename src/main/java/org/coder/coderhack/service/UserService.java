package org.coder.coderhack.service;

import org.coder.coderhack.dto.UserRegistrationDto;
import org.coder.coderhack.dto.UserScoreUpdateDto;

public interface UserService {

    /**
     * Register a user
     * @param userRegistrationDto
     */
    void registerUser(UserRegistrationDto userRegistrationDto);

    /**
     * Update user score
     * @param userScoreUpdateDto
     * @return
     */
    boolean updateUserScore(UserScoreUpdateDto userScoreUpdateDto);


}
