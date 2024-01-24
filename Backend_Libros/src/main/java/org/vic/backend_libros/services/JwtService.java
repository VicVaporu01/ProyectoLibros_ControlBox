package org.vic.backend_libros.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // Clave secreta para firmar los JWT
    private String jwtSecretKey = generateSecretKey();

    // Genera un token para un usuario
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    // Genera un token con reclamaciones adicionales para un usuario
    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getKey())
                .compact();
    }

    // Genera una clave secreta para firmar los JWT
    public String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[32]; // 32 bytes = 256 bits
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

    // Obtiene la clave secreta para firmar los JWT
    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Obtiene el correo del usuario a partir del token
    public String getCorreoFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    // Verifica si un token es válido para un usuario
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getCorreoFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Obtiene todas las reclamaciones de un token
    private Claims getAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Obtiene una reclamación específica de un token
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Verifica si un token ha expirado
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    // Obtiene la fecha de expiración de un token
    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

}