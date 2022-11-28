package com.example.dogadjaji213.service.user;

import com.example.dogadjaji213.model.AppUser;
import com.example.dogadjaji213.model.Role;
import com.example.dogadjaji213.repository.RoleRepository;
import com.example.dogadjaji213.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService,UserDetailsService {
    private final UserRepository _userRepository;
    private final RoleRepository _roleRepository;
    @Override
    public AppUser saveUser(AppUser appUser) {
        AppUser user = this._userRepository.findByEmail(appUser.getEmail());
        if(user != null){
            throw new IllegalStateException("User already exists!");
        }
        this._userRepository.save(appUser);
        return appUser;
    }

    @Override
    public Role saveRole(Role saveRole) {
        return null;
    }

    @Override
    public void addRoleToUser(String email, String roleName) {

    }

    @Override
    public AppUser getUser(String email) {
        return null;
    }

    @Override
    public List<AppUser> getUsers() {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
