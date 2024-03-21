package org.coder.coderhack.service;

import org.coder.coderhack.dto.UserRegistrationDto;
import org.coder.coderhack.dto.UserScoreUpdateDto;
import org.coder.coderhack.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    /**
     * Register a user
     *
     * @param userRegistrationDto
     */
    void registerUser(UserRegistrationDto userRegistrationDto);

    /**
     * Update user score
     *
     * @param userScoreUpdateDto
     * @return
     */
    boolean updateUserScore(String userId, UserScoreUpdateDto userScoreUpdateDto);

    /**
     * Get all users
     *
     * @return
     */
    List<User> getAllUsers();

    /**
     * Get user by id
     *
     * @param userId
     * @return
     */
    User getUserById(String userId);

    /**
     * Delete user by id
     *
     * @param userId
     * @return
     */
    boolean deleteUserById(String userId);
}
