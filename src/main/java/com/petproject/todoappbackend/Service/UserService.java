package com.petproject.todoappbackend.Service;

import com.petproject.todoappbackend.Model.User;
import com.petproject.todoappbackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean ifUserExists(String userName) {
        return userRepository.existsByUserName(userName);
    }

    public void createUser(Map<String, String> userInfo) {
        String userName = userInfo.get("userName");
        String password = userInfo.get("password");
        User user = User.builder()
                .userName(userName)
                .password(password)
                .build();
        userRepository.save(user);
    }

    public boolean isValidUser(Map<String, String> userInfo) {
        String userName = userInfo.get("userName");
        String password = userInfo.get("password");
        return userRepository.existsByUserNameAndPassword(userName, password);
    }
}
