package com.LoginAuth.Login.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    // Injetando os valores do application.properties
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration.ms}")
    private long jwtExpiration;

    // --- MÉTODOS PÚBLICOS PRINCIPAIS ---

    /**
     * Extrai o nome de usuário (e-mail) do token JWT.
     */
    public String extractUsername(String token) {
        // A lógica para extrair o "subject" (que será nosso e-mail) do token.
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Gera um token JWT para um usuário autenticado.
     */
    public String generateToken(UserDetails userDetails) {
        // Lógica para construir o token com claims, subject, data de emissão,
        // data de expiração, e assinar com a chave secreta.
        String username = userDetails.getUsername();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Verifica se um token JWT é válido para um determinado usuário.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        // A lógica para verificar se o usuário no token é o mesmo que o userDetails
        // E se o token não está expirado.
        return ((userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token)));
    }


    // --- MÉTODOS AUXILIARES PRIVADOS ---

    /**
     * Verifica se o token expirou.
     */
    private boolean isTokenExpired(String token) {
        // Lógica para extrair a data de expiração e comparar com a data atual.
        Date expirationDate = extractClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }
    
    /**
     * Retorna a chave de assinatura gerada a partir da nossa chave secreta.
     */
    private Key getSignInKey() {
        // Lógica para decodificar a chave secreta Base64 e criar um objeto Key.
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    /**
     * Método genérico para extrair qualquer informação (claim) de um token.
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        // Lógica para parsear o token, validar a assinatura e extrair todos os claims.
        // A partir dos claims, aplica a função recebida para obter a informação desejada.
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}