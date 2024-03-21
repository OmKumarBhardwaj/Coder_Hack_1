package org.coder.coderhack.service;


import org.coder.coderhack.constant.Badge;
import org.coder.coderhack.dto.UserRegistrationDto;
import org.coder.coderhack.dto.UserScoreUpdateDto;
import org.coder.coderhack.entity.User;
import org.coder.coderhack.exception.MaximumBatchesAchieved;
import org.coder.coderhack.exception.UserAlreadyExistsException;
import org.coder.coderhack.exception.UserNotFoundException;
import org.coder.coderhack.exception.UserScoreAlreadyModifiedOnce;
import org.coder.coderhack.repository.UserRepository;
import org.coder.coderhack.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.coder.coderhack.constant.Badge.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private UserRegistrationDto userRegistrationDto;
    private User mockUser;
    private UserScoreUpdateDto scoreUpdateDto;

    private User user1;
    private User user2;
    private User user3;
    List<User> users;

    private

    @BeforeEach
    void setUp() {
        userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setUserId("1");
        userRegistrationDto.setUserName("Om");

        mockUser = new User();
        mockUser.setUserId("1");
        mockUser.setUserName(("Om"));
        mockUser.setScore(0);
        mockUser.setBadges(new LinkedHashSet<>());

        scoreUpdateDto = new UserScoreUpdateDto();
        scoreUpdateDto.setScore(50);

        user1 = new User("1", "User1", 30, new LinkedHashSet<>());
        user2 = new User("2", "User2", 60, new LinkedHashSet<>());
        user3 = new User("3", "User3", 10, new LinkedHashSet<>());
        users = Arrays.asList(user1, user2, user3);
    }


    // Testing User Already exists
    @Test
    void registerUser_UserAlreadyExists_ThrowsException() {
        when(userRepository.findById(anyString())).thenReturn(Optional.of(new User()));

        assertThrows(UserAlreadyExistsException.class, () -> userServiceImpl.registerUser(userRegistrationDto));

        verify(userRepository, times(1)).findById(userRegistrationDto.getUserId());
        verify(userRepository, never()).save(any(User.class));
    }

    // Testing successful user creation
    @Test
    void registerUser_SuccessfulRegistration_RegistersUser() {
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());

        userServiceImpl.registerUser(userRegistrationDto);

        verify(userRepository, times(1)).findById(userRegistrationDto.getUserId());
        verify(userRepository, times(1)).save(any(User.class));
    }

    // Testing UserNotFound in updateUserScore
    @Test
    void updateUserScore_UserNotFound_ThrowsException() {
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userServiceImpl.updateUserScore(mockUser.getUserId(), scoreUpdateDto));

        verify(userRepository, times(1)).findById(mockUser.getUserId());
    }

    // Testing Maximum Badges Achieved
    @Test
    void updateUserScore_MaxBadgesAchieved_ThrowsException() {
        mockUser.getBadges().addAll(Set.of(CODE_NINJA, CODE_MASTER, CODE_CHAMP, ANOTHER_BADGE));
        when(userRepository.findById(anyString())).thenReturn(Optional.of(mockUser));
        assertThrows(MaximumBatchesAchieved.class, () -> userServiceImpl.updateUserScore(mockUser.getUserId(), scoreUpdateDto));
        verify(userRepository, times(1)).findById(mockUser.getUserId());
    }

    // Testing UserScoreAlreadyModifiedOnce
    @Test
    void updateUserScore_ScoreAlreadyModified_ThrowsException() {
        mockUser.setScore(10);
        when(userRepository.findById(anyString())).thenReturn(Optional.of(mockUser));
        assertThrows(UserScoreAlreadyModifiedOnce.class, () -> userServiceImpl.updateUserScore(mockUser.getUserId(), scoreUpdateDto));
        verify(userRepository, times(1)).findById(mockUser.getUserId());
    }

    // Testing Successful Update
    @Test
    void updateUserScore_SuccessfulUpdate_UpdatesScoreAndBadges() {
        when(userRepository.findById(anyString())).thenReturn(Optional.of(mockUser));
        userServiceImpl.updateUserScore(mockUser.getUserId(), scoreUpdateDto);
        assertEquals(50, mockUser.getScore());
        assertTrue(mockUser.getBadges().contains(Badge.CODE_CHAMP));
        verify(userRepository, times(1)).findById(mockUser.getUserId());
        verify(userRepository, times(1)).save(mockUser);
    }

    // Testing NoUsersFound for getAllUsers
    @Test
    void getAllUsers_NoUsersFound_ThrowsException() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.getAllUsers());
        verify(userRepository, times(1)).findAll();
    }

    // Testing orders of users in getAllUsers according to score
    @Test
    void getAllUsers_UsersFound_ReturnsSortedUsers() {
        when(userRepository.findAll()).thenReturn(users);

        List<User> sortedUsers = userServiceImpl.getAllUsers();

        assertEquals(3, sortedUsers.size());
        assertEquals(user2, sortedUsers.get(0));
        assertEquals(user1, sortedUsers.get(1));
        assertEquals(user3, sortedUsers.get(2));

        verify(userRepository, times(1)).findAll();
    }

    // Testing UserNotFound for getUserById
    @Test
    void getUserById_UserNotFound_ThrowsException() {
        when(userRepository.findById("1")).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUserById("1"));
        verify(userRepository, times(1)).findById("1");
    }

    // Testing User found for getUserById
    @Test
    void getUserById_UserFound_ReturnsUser() {
        when(userRepository.findById("1")).thenReturn(Optional.of(mockUser));
        User result = userServiceImpl.getUserById("1");

        assertNotNull(result);
        assertEquals("1", result.getUserId());
        assertEquals("Om", result.getUserName());

        verify(userRepository, times(1)).findById("1");
    }

    // Testing UserNotFound for deleteUserById
    @Test
    void deleteById_UserNotFound_ThrowsException() {
        when(userRepository.findById("1")).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.deleteUserById("1"));
        verify(userRepository, times(1)).findById("1");
    }

    // Testing Successful Deletion
    @Test
    void deleteById_SuccessfulDeletion_DeletesUser() {
        when(userRepository.findById("1")).thenReturn(Optional.of(mockUser));
        userServiceImpl.deleteUserById("1");
        verify(userRepository, times(1)).findById("1");
        verify(userRepository, times(1)).deleteById("1");
    }

}
