package com.virus.controller;

import com.virus.entity.User;
import com.virus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/")
    public String home() {
        return "Base";
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "Login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String successfulLogin() {
        return "auth/Dashboard";
    }

    @RequestMapping(value = "/form-register", method = RequestMethod.POST)
    public String formRegister(@ModelAttribute("formData") User user) {
        user.setRole("USER");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Login";
    }

    @RequestMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute("formData", new User());
        return "SignUp";
    }
}