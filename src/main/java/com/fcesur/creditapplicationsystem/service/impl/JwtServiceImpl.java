package com.fcesur.creditapplicationsystem.service.impl;

import com.fcesur.creditapplicationsystem.entity.User;
import com.fcesur.creditapplicationsystem.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
@Getter
public class JwtServiceImpl implements JwtService {
    private static final String JWT_SECRET_KEY = "7134743777217A25432A462D4A614E645267556B58703272357538782F413F44";

    private static final Integer JWT_EXPIRATION_MS = 60 * 1000 * 24;

    @Override
    public String findUsername(String jwtToken) {
        return exportToken(jwtToken, Claims::getSubject);
    }

    @Override
    public boolean tokenControl(String jwtToken, UserDetails userDetails) {
        final String username = findUsername(jwtToken);

        return (username.equals(userDetails.getUsername()) && !exportToken(jwtToken, Claims::getExpiration).before(new Date()));
    }

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private <T> T exportToken(String jwtToken, Function<Claims, T> claimsTFunction) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();

        return claimsTFunction.apply(claims);
    }

    private Key getSignInKey() {
        byte[] key = Decoders.BASE64.decode(JWT_SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }
}
