package com.virus.controller;

import com.virus.entity.User;
import com.virus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class _Controller {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/get")
    public List<User> get(){
        return userRepository.findAll();
    }
}
