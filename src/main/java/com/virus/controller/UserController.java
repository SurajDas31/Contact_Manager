package com.virus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class UserController {
    @RequestMapping("/")
    public String dashboard(){
        return "auth/Dashboard";
    }
}
