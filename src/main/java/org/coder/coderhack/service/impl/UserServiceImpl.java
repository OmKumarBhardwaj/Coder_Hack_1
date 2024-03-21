package org.coder.coderhack.service.impl;

import lombok.AllArgsConstructor;
import org.coder.coderhack.constant.Badge;
import org.coder.coderhack.dto.UserRegistrationDto;
import org.coder.coderhack.dto.UserScoreUpdateDto;
import org.coder.coderhack.entity.User;
import org.coder.coderhack.exception.MaximumBatchesAchieved;
import org.coder.coderhack.exception.UserAlreadyExistsException;
import org.coder.coderhack.exception.UserNotFoundException;
import org.coder.coderhack.exception.UserScoreAlreadyModifiedOnce;
import org.coder.coderhack.repository.UserRepository;
import org.coder.coderhack.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
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

    @Override
    public boolean updateUserScore(String userId, UserScoreUpdateDto userScoreUpdateDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with given user id: " + userId));

        if (user.getBadges().size() > 3) {
            throw new MaximumBatchesAchieved("User already achieved maximum badges");
        }

        if (user.getScore() > 0) {
            throw new UserScoreAlreadyModifiedOnce("User score already modified once");
        }

        addBadgesBasedOnScore(user, userScoreUpdateDto.getScore());
        user.setScore(userScoreUpdateDto.getScore());

        userRepository.save(user);

        return true;
    }

    private void addBadgesBasedOnScore(User user, int score) {
        if (score >= 1 && score <= 30) {
            user.getBadges().add(Badge.CODE_NINJA);
        } else if (score > 30 && score <= 60) {
            user.getBadges().addAll(Arrays.asList(Badge.CODE_NINJA, Badge.CODE_CHAMP));
        } else if (score > 60 && score <= 100) {
            user.getBadges().addAll(Arrays.asList(Badge.CODE_NINJA, Badge.CODE_CHAMP, Badge.CODE_MASTER));
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            throw new UserNotFoundException("No users found");
        }
        userList.sort((user1, user2) -> user2.getScore().compareTo(user1.getScore()));
        return userList;
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById((userId))
                .orElseThrow(() -> new UserNotFoundException("User not found with given user id:" + userId));
    }

    @Override
    public boolean deleteUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with given user id: " + userId));

        userRepository.deleteById(user.getUserId());
        return true;
    }
}