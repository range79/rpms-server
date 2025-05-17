package com.range.rpms.common.util;

import com.range.rpms.user.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtUtil {
    @Value(value = "${app.jwt-secret}")
    private String secret;
    @Value(value = "${app.jwt-duration}")
    private int expiresIn;
    public String generateToken(String username, Role role){
        return Jwts
                .builder().subject(username)
                .claim("role",role.name()).signWith(getSecretKey())
                .expiration(new Date(System.currentTimeMillis()+expiresIn*1000L))
                .compact();
    }

    public SecretKey getSecretKey() {
        return Keys
                .hmacShaKeyFor(secret
                        .getBytes()
                );
    }
    public Claims parseToken(String token) {
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsername(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {

      Claims claim =  parseToken(token);

      String username = claim.getSubject();


      Date expiration = claim.getExpiration();

      boolean expired = expiration.before(new Date());

      return username.equals(userDetails.getUsername())&&!expired;

    }




}
