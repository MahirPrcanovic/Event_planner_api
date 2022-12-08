package com.example.dogadjaji213.config;

import com.example.dogadjaji213.service.user.UserService;
import com.example.dogadjaji213.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil _jwtUtil;
    @Autowired
    private UserService _userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      final String header= request.getHeader("Authorization");
      String jwtToken=null;
      String username=null;
      if(header!=null && header.startsWith("Bearer ")){
          jwtToken= header.substring(7);
          try{
            username =this._jwtUtil.getUserNameFromToken(jwtToken);
          }catch(IllegalArgumentException ex){
              System.out.println("Token not available");
          }catch(ExpiredJwtException ex){
              System.out.println("Token has expired");
          }
      }else{
          System.out.println("Jwt token does not start with Bearer");
      }
      if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null){
        UserDetails user = this._userService.loadUserByUsername(username);

        if(_jwtUtil.validateToken(jwtToken,user)){
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(token);
        }
      }
      filterChain.doFilter(request,response);
    }
}
