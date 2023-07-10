package com.security.demo.JWTConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JWTService {

    private String secret_key = "45774a4a9b3ab452ea91681d20a1ace04168ebde939d88e7ee647eeacf9d487a";


    public String generateToken(String email) {
        return "Bearer " + Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 604800000))
                .signWith(getSingKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSingKey())
                .build()
                .parseClaimsJws(token)
                .getBody();


    }

    public Boolean tokenIsValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token).getSubject();
        return (username.equals(userDetails.getUsername()) && !tokenExpiration(token));
    }


    private Boolean tokenExpiration(String token) {
        return extractUsername(token).getExpiration().before(new Date());
    }

    private Key getSingKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
