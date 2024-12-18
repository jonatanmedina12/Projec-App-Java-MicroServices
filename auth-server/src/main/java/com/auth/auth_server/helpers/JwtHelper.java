package com.auth.auth_server.helpers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtHelper {  // Cambié el nombre a PascalCase

    @Value("${jwt.secret}")  // Añadí la referencia a la propiedad
    private String jwtSecret;

    public String createToken(String username) {  // Cambié a public
        final var now = new Date();
        final var expirationDate = new Date(now.getTime() + (3600 * 1000));

        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(now)  // Simplifiqué usando now
                .setExpiration(expirationDate)
                .signWith(getSecretKey())
                .compact();
    }

    public boolean validateToken(String token) {  // Cambié a public
        final var expirationDate = getExpirationDate(token);
        return expirationDate.after(new Date());
    }

    private Date getExpirationDate(String token) {
        return getClaimsFromToken(token, Claims::getExpiration);  // Corregí la referencia al método
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> resolver) {
        return resolver.apply(parseToken(token));  // Cambié nombre del método para mayor claridad
    }

    private Claims parseToken(String token) {  // Cambié nombre del método
        return Jwts
                .parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
}