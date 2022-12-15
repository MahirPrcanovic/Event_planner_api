package com.example.dogadjaji213.service.user;

import com.example.dogadjaji213.dto.JwtResponse;
import com.example.dogadjaji213.dto.user.RegisterReqDto;
import com.example.dogadjaji213.dto.user.UserCreatedResDto;
import com.example.dogadjaji213.dto.user.UserLoginReqDto;
import com.example.dogadjaji213.model.AppUser;
import com.example.dogadjaji213.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IUserService {
    UserCreatedResDto saveUser(RegisterReqDto appUser) throws Exception;
    Role saveRole(Role saveRole);
    void addRoleToUser(String email,String roleName);
    AppUser getUser(UUID id);
    List<AppUser> getUsers();
    AppUser changePassword(String password);
    void updateIsBanned(UUID id) throws Exception;
    JwtResponse createToken(UserLoginReqDto user) throws Exception;
}
