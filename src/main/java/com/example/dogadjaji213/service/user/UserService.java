package com.example.dogadjaji213.service.user;

import com.example.dogadjaji213.dto.JwtResponse;
import com.example.dogadjaji213.dto.user.RegisterReqDto;
import com.example.dogadjaji213.dto.user.UserCreatedResDto;
import com.example.dogadjaji213.dto.user.UserLoginReqDto;
import com.example.dogadjaji213.model.AppUser;
import com.example.dogadjaji213.model.Role;
import com.example.dogadjaji213.repository.RoleRepository;
import com.example.dogadjaji213.repository.UserRepository;
import com.example.dogadjaji213.utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService,UserDetailsService {
    private final UserRepository _userRepository;
    private final RoleRepository _roleRepository;
    private final PasswordEncoder _passwordEncoder;
    private final JwtUtil _jwtUtil;
    @Override
    public UserCreatedResDto saveUser(RegisterReqDto appUser) {
        AppUser user = new AppUser(appUser.getFirstName(), appUser.getLastName(), appUser.getEmail(), appUser.getPassword());
        user.setPassword(this._passwordEncoder.encode(appUser.getPassword()));
        var returnUser=this._userRepository.save(user);
        this.addRoleToUser(appUser.getEmail(),"USER");
        return new UserCreatedResDto(returnUser.getId(),returnUser.getFirstName(),returnUser.getLastName(),returnUser.getEmail());
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
    public AppUser changePassword(String pwd) {
        System.out.println(pwd);
        AppUser appUser= this._userRepository.findByEmail("mahirprcanovic@gmail.com"); /*Admin je samo jedan korisnik i on je postavljen na mahirprcanovic@gmail.com account*/
        appUser.setPassword(this._passwordEncoder.encode(pwd));
        return this._userRepository.save(appUser);

    }

    @Override
    public void updateIsBanned(UUID id) {
        AppUser appUser = this._userRepository.findById(id).get();
        if(appUser == null) throw new IllegalStateException("User does not exist!");
        appUser.setIsBanned(!appUser.getIsBanned());
        this._userRepository.save(appUser);
    }

    @Override
    public JwtResponse createToken(UserLoginReqDto user) throws Exception {
        String username = user.getUsername();
        String password = user.getPassword();
        authenticate(username,password);
        final UserDetails userDetails = loadUserByUsername(username);
        String newGeneratedToken= this._jwtUtil.generateToken(userDetails);
        AppUser appUser = this._userRepository.findByEmail(username);
        return new JwtResponse(username,newGeneratedToken);
    }
    private void authenticate(String username,String password) throws Exception{

            AppUser authUser = this._userRepository.findByEmail(username);
            if( authUser == null ) throw new DisabledException("");
            boolean passwordCorrect= this._passwordEncoder.matches(password,authUser.getPassword());
            if(!passwordCorrect) throw new BadCredentialsException("Passwords do not match");

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
