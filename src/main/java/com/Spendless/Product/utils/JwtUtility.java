package com.Spendless.Product.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtility {


    private final long accessTokenExpiryTime = 86400000L;
    private final long refreshTokenExpiryTime = 2592000000L;
    String jwtSecretKey = "dsamdnash32qw903u0riowqejoirj3290roijaskflnlksznfklnsanfasf";
    private SecretKey key = Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));

    public String generateAccessToken(String email) {
        System.out.println("Access token " + accessTokenExpiryTime);
        return Jwts.builder().subject(email).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + accessTokenExpiryTime))
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(String email){
        return Jwts.builder().subject(email).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + refreshTokenExpiryTime))
                .signWith(key)
                .compact();
    }




    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)   // ✅ replaces setSigningKey
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }


}
