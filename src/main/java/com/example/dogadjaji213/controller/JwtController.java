package com.example.dogadjaji213.controller;

import com.example.dogadjaji213.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {
    @Autowired
    private UserService userService;
    public void createJwtToken(){

    }
}
