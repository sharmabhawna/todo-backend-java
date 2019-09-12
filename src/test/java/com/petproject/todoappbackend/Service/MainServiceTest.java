package com.petproject.todoappbackend.Service;

import com.petproject.todoappbackend.Repository.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class MainServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService mainService;
}