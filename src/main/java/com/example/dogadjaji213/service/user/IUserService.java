package com.example.dogadjaji213.service.user;

import com.example.dogadjaji213.dto.RegisterDto;
import com.example.dogadjaji213.model.AppUser;
import com.example.dogadjaji213.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    AppUser saveUser(RegisterDto appUser);
    Role saveRole(Role saveRole);
    void addRoleToUser(String email,String roleName);
    AppUser getUser(String email);
    List<AppUser> getUsers();
    void changePassword(String password);
    void updateIsBanned(String email);
}
