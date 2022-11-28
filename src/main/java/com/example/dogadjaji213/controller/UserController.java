package com.example.dogadjaji213.controller;

import com.example.dogadjaji213.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController @RequiredArgsConstructor
@RequestMapping("/")
public class UserController {

    private final UserService _userService;
    @GetMapping
    public String getAll(){
        return "hello";
    }
}
