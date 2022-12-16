package com.example.dogadjaji213.controller;

import com.example.dogadjaji213.dto.GlobalResponseDto;
import com.example.dogadjaji213.dto.JwtResponse;
import com.example.dogadjaji213.dto.user.ChangePassDto;
import com.example.dogadjaji213.dto.user.RegisterReqDto;
import com.example.dogadjaji213.dto.user.UserLoginReqDto;
import com.example.dogadjaji213.model.AppUser;
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
import java.util.Optional;
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
            return ResponseEntity.badRequest().body("User with your email does not exist");
        }catch(BadCredentialsException ex){
            return ResponseEntity.badRequest().body("Password is not correct.");
        }

    }
    @GetMapping("/hi")
    public String get(){
        return "hello";
    }
    @PostMapping("/save")
    public ResponseEntity<GlobalResponseDto> register(@RequestBody RegisterReqDto registerDto){
        var response = new GlobalResponseDto();
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/save").toUriString());
        try{
            this._userService.saveUser(registerDto);
            response.setMessage("Successfully registered.".describeConstable());
            return ResponseEntity.created(uri).body(response);
        }catch(Exception ex){
            response.setSuccess(false);
            response.setMessage(ex.getMessage().describeConstable());
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PutMapping("/changepass")
    public ResponseEntity<GlobalResponseDto> changePass(@RequestBody ChangePassDto changePassDto) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/changepass").toUriString());
        var response = new GlobalResponseDto();
        try{
            AppUser user = this._userService.changePassword(changePassDto);
            response.setMessage("Success".describeConstable());
            response.setItem(Optional.ofNullable(user));
            return ResponseEntity.ok().body(response);
        }catch(Exception ex){
            response.setSuccess(false);
            response.setMessage(ex.getMessage().describeConstable());
            return ResponseEntity.badRequest().body(response);
        }

    }
    @PatchMapping("/ban/{id}")
    public ResponseEntity<GlobalResponseDto> updateUserBanned(@PathVariable UUID id){
        var response = new GlobalResponseDto();
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/ban/{id}").toUriString());
        try{
            this._userService.updateIsBanned(id);
            response.setMessage("Success".describeConstable());
            return ResponseEntity.created(uri).body(response);
        }catch(Exception ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage().describeConstable());
            return ResponseEntity.badRequest().body(response);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponseDto> getSinge(@PathVariable UUID id){
        var response = new GlobalResponseDto();
        try{
            var user = this._userService.getUser(id);
            if(user == null) throw new Exception("User does not exist.");
            response.setMessage("Success".describeConstable());
            response.setItem(Optional.of(user));
            return ResponseEntity.ok().body(response);
        }catch(Exception ex){
            response.setSuccess(false);
            response.setMessage(ex.getMessage().describeConstable());
            return (ResponseEntity<GlobalResponseDto>) ResponseEntity.notFound();
        }
    }
}
