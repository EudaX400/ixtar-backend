package com.eud.ixtar.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import com.eud.ixtar.users.User;

import java.util.Date;

import javax.crypto.SecretKey;

@Component
public class JwtUtils {

    private static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key) 
                .compact();
    }

    public static SecretKey getKey() {
        return key;
    }
}
