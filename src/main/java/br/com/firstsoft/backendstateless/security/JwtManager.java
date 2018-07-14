package br.com.firstsoft.backendstateless.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtManager {

    @Autowired
    private Environment environment;

    public String decode(String token) {
        return Jwts.parser()
                .setSigningKey(environment.getProperty("backend-stateless.token-secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String encode(String subject) {
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + Long.valueOf(environment.getProperty("backend-stateless.token-lifetime"))))
                .signWith(SignatureAlgorithm.HS512, environment.getProperty("backend-stateless.token-secret"))
                .compact();
    }

}
