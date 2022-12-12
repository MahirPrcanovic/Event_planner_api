package com.example.dogadjaji213.utils;

import com.example.dogadjaji213.model.AppUser;
import com.example.dogadjaji213.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private static final String SECRET_KEY = "notGoodIdeaLeavingThisSeenButWhatever";
    private static final Integer TOKEN_VALIDITY=3600*5;
    private final UserRepository _userRepository;

    public String getUserNameFromToken(String token){
        return getClaimFromToken(token,Claims::getSubject);
    }
    private <T> T getClaimFromToken(String token, Function<Claims,T> claimResolver){
       final Claims claims = getAllClaimsFromToken(token);
       return claimResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    public boolean validateToken(String token, UserDetails userDetails){
       String username= getUserNameFromToken(token);
       return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token){
        final Date date = getExpirationDateFromToken(token);
        return date.before(new Date());
    }
    private Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }
    public String generateToken(UserDetails userDetails){
        AppUser usr= this._userRepository.findByEmail(userDetails.getUsername());
        Map<String,Object> claims = new HashMap<>();
        claims.put("role",usr.getRole().getName());
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY).compact();
    }
}
