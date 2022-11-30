package com.example.dogadjaji213.service.user;

import com.example.dogadjaji213.dto.RegisterReqDto;
import com.example.dogadjaji213.dto.UserCreatedResDto;
import com.example.dogadjaji213.model.AppUser;
import com.example.dogadjaji213.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IUserService {
    UserCreatedResDto saveUser(RegisterReqDto appUser);
    Role saveRole(Role saveRole);
    void addRoleToUser(String email,String roleName);
    AppUser getUser(String email);
    List<AppUser> getUsers();
    void changePassword(String password);
    void updateIsBanned(UUID id);
}
