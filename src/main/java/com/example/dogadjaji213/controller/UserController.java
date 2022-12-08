package com.example.dogadjaji213.controller;

import com.example.dogadjaji213.dto.JwtResponse;
import com.example.dogadjaji213.dto.user.ChangePassDto;
import com.example.dogadjaji213.dto.user.RegisterReqDto;
import com.example.dogadjaji213.dto.user.UserLoginReqDto;
import com.example.dogadjaji213.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController @RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    private final UserService _userService;
    /*private final AuthenticationManager _authenticationManager;*/
/*    @PostMapping
    @RequestMapping("/login")
    public Authentication login(@RequestBody UserLoginReqDto user){
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication auth = this._authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        return auth;
    }*/
    @PostMapping("/login")
    public ResponseEntity<?> createJwtToken(@RequestBody UserLoginReqDto user) throws Exception {
        try{
            return ResponseEntity.ok().body(this._userService.createToken(user));
        }catch(DisabledException ex){
            return ResponseEntity.badRequest().body("User not found");
        }catch(BadCredentialsException ex){
            return ResponseEntity.badRequest().body("Credentials dont match");
        }

    }
    @GetMapping("/hi")
    public String get(){
        return "hello";
    }
    @PostMapping("/save")
    public ResponseEntity<?> register(@RequestBody RegisterReqDto registerDto){

        return ResponseEntity.ok().body(this._userService.saveUser(registerDto));
    }
    @PutMapping("/changepass")
    public ResponseEntity<?> changePass(@RequestBody ChangePassDto changePassDto){
        System.out.println(changePassDto.getPassword());
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/changepass").toUriString());
        this._userService.changePassword(changePassDto.getPassword());
        return ResponseEntity.ok().body(this._userService.changePassword(changePassDto.getPassword()));
    }
    @PatchMapping("/ban/{id}")
    public ResponseEntity<?> updateUserBanned(@PathVariable UUID id){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/ban/{id}").toUriString());
        this._userService.updateIsBanned(id);
        return ResponseEntity.created(uri).body("");
    }
}
