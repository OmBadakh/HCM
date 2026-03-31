package com.hrms.hrms_backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // ✅ Token expiry
    private final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 60; // 1 hour
    private final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24; // 24 hours

    // ✅ FIXED SECRET KEY (MUST be at least 32 chars)
    private final String SECRET = "mysecretkeymysecretkeymysecretkey12345";

    // ✅ Generate signing key
    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // =========================
    // 🔐 Generate Access Token
    // =========================
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // =========================
    // 🔄 Generate Refresh Token
    // =========================
    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // =========================
    // 📥 Extract Username
    // =========================
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // =========================
    // 📥 Extract Role
    // =========================
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // =========================
    // 📥 Extract Claims
    // =========================
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // =========================
    // ✅ Validate Token
    // =========================
    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    // =========================
    // ⏳ Check Expiry
    // =========================
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}