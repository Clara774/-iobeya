package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<User> findByUsername(String username);
}
