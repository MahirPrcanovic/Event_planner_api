package com.example.dogadjaji213.controller;

import com.example.dogadjaji213.dto.RegisterDto;
import com.example.dogadjaji213.model.AppUser;
import com.example.dogadjaji213.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController @RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService _userService;
    @GetMapping("/hi")
    public String get(){
        return "hello";
    }
    @PostMapping("/user/save")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto){
        this._userService.saveUser(registerDto);
        return ResponseEntity.ok().body(registerDto);
    };
}
