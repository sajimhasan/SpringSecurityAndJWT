package com.example.SecurityMarginofSpringBoot.Service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.crypto.Data;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class JWTService {

private String secretKey ="";

    public JWTService(){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk =keyGenerator.generateKey();
          secretKey =  Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String genearetedToken(String username){

        Map<String , Object> claims=new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
                .and()
                .signWith(getkey())
                .compact();
    }

    public Key getkey(){
        byte[] keybyte = Decoders.BASE64. decode (secretKey);
        return Keys.hmacShaKeyFor(keybyte);
    }

    public String extratUserName(String token) {
        return "";
    }

    public boolean vaildatetoken(String token, UserDetails userDetails) {
        return true;

    }
}
