package com.test.service;


import org.springframework.stereotype.Service;

import com.test.model.User;

@Service
public class UserService {

    public User getUserById(Long id) {
        
        return new User(id, "abc", "abc@example.com");
    }
}

