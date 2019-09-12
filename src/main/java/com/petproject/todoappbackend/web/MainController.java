package com.petproject.todoappbackend.web;

import com.petproject.todoappbackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/signUp")
    ResponseEntity<String> signUp(@RequestBody Map<String, String> userInfo) {
        String userName = userInfo.get("userName");

        if (userService.ifUserExists(userName)) {
            return new ResponseEntity<>("User with given name already exits", HttpStatus.BAD_REQUEST);
        }
        userService.createUser(userInfo);
        return new ResponseEntity<>("Successfully signed up", HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    ResponseEntity<String> login(@RequestBody Map<String, String> userInfo) {
        if (userService.isValidUser(userInfo)) {
            return new ResponseEntity<>("Successfully logged in", HttpStatus.OK);
        }
        return new ResponseEntity<>("Wrong user name or password", HttpStatus.BAD_REQUEST);
    }

}
