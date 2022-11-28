package com.example.dogadjaji213.service.user;

import com.example.dogadjaji213.model.AppUser;
import com.example.dogadjaji213.model.Role;
import com.example.dogadjaji213.repository.RoleRepository;
import com.example.dogadjaji213.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService,UserDetailsService {
    private final UserRepository _userRepository;
    private final RoleRepository _roleRepository;
    private final PasswordEncoder _passwordEncoder;
    @Override
    public AppUser saveUser(AppUser appUser) {
        appUser.setPassword(this._passwordEncoder.encode(appUser.getPassword()));
        return this._userRepository.save(appUser);
    }

    @Override
    public Role saveRole(Role saveRole) {
        Role role = this._roleRepository.findByName(saveRole.getName());
        if(role !=null){
            throw new IllegalStateException("Role already exists");
        }
        this._roleRepository.save(saveRole);
        return saveRole;
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        AppUser user = this._userRepository.findByEmail(email);
        Role role = this._roleRepository.findByName(roleName);
        if(user == null || role == null){
            throw new IllegalStateException("User or role are not found.");
        }
        user.setRole(role);
    }

    @Override
    public AppUser getUser(String email) {
        AppUser user = this._userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public List<AppUser> getUsers() {
        List<AppUser> users  = this._userRepository.findAll();
        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = this._userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User not found!");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }
}
