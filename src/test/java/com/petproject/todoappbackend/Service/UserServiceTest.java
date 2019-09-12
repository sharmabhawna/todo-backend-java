package com.petproject.todoappbackend.Service;

import com.petproject.todoappbackend.Model.User;
import com.petproject.todoappbackend.Repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    public void shouldReturnIfAnUserAlreadyExistsOrNot() {
        when(userRepository.existsByUserName("abc")).thenReturn(false);
        when(userRepository.existsByUserName("xyz")).thenReturn(true);

        assertFalse(userService.ifUserExists("abc"));
        assertTrue(userService.ifUserExists("xyz"));
    }

    @Test
    public void shouldCreateNewUser() {
        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("userName", "abc");
        userInfo.put("password", "xyz");

        String userName = userInfo.get("userName");
        String password = userInfo.get("password");

        User user = User.builder()
                .userName(userName)
                .password(password)
                .build();
        userService.createUser(userInfo);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void shouldValidateUser() {
        HashMap<String, String> validUserInfo = new HashMap<>();
        validUserInfo.put("userName", "abc");
        validUserInfo.put("password", "xyz");

        HashMap<String, String> invalidUserInfo = new HashMap<>();
        invalidUserInfo.put("userName", "xyz");
        invalidUserInfo.put("password", "abc");

        when(userRepository.existsByUserNameAndPassword("abc", "xyz")).thenReturn(true);
        when(userRepository.existsByUserNameAndPassword("xyz", "abc")).thenReturn(false);

        assertTrue(userService.isValidUser(validUserInfo));
        assertFalse(userService.isValidUser(invalidUserInfo));
    }
}