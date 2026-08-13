package com.war.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * JWT 服务
 * 负责生成、解析和验证 JWT Token
 */
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    /**
     * 获取签名密钥
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 从用户名生成 JWT Token
     */
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * 从 Token 中获取用户名
     */
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    /**
     * 获取 Token 过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getExpiration();
    }

    /**
     * 验证 Token 是否有效
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (SignatureException e) {
            // 签名无效
            return false;
        } catch (ExpiredJwtException e) {
            // Token 已过期
            return false;
        } catch (UnsupportedJwtException e) {
            // 不支持的 Token
            return false;
        } catch (MalformedJwtException e) {
            // Token 格式错误
            return false;
        } catch (IllegalArgumentException e) {
            // Token 为空或 null
            return false;
        }
    }

    /**
     * 检查 Token 是否即将过期（5 分钟内）
     */
    public boolean isTokenExpiringSoon(String token) {
        try {
            Date expiration = getExpirationDateFromToken(token);
            long now = System.currentTimeMillis();
            long expirationTime = expiration.getTime();
            
            // 如果剩余时间少于 5 分钟，认为即将过期
            return (expirationTime - now) < 5 * 60 * 1000;
        } catch (Exception e) {
            return true;
        }
    }
}
