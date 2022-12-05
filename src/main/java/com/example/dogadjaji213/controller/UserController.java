package com.example.dogadjaji213.controller;

import com.example.dogadjaji213.dto.RegisterReqDto;
import com.example.dogadjaji213.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController @RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService _userService;
    @GetMapping("/hi")
    public String get(){
        return "hello";
    }
    @PostMapping("/user/save")
    public ResponseEntity<?> register(@RequestBody RegisterReqDto registerDto){

        return ResponseEntity.ok().body(this._userService.saveUser(registerDto));
    }
    @PutMapping("/changepass")
    public String changePass(@RequestBody String password){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/changepass").toUriString());
        this._userService.changePassword(password);
        return "success";
    }
    @PatchMapping("/ban/{id}")
    public ResponseEntity<?> updateUserBanned(@PathVariable UUID id){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/ban/{id}").toUriString());
        this._userService.updateIsBanned(id);
        return ResponseEntity.created(uri).body("");
    }
}
