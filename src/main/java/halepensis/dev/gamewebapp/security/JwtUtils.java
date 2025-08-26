package halepensis.dev.gamewebapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {
    private final String secret = "ggmxg88f0imDegLbPhRCXq9zE6CBqY86L6XhRZh31JcTjFVqkuwaX8MKOSGe8bUjgwmCZKLb3OmoKn1x5ZtJNfAAjHhoZwxaPH8GgplQQHGnnNavZBag3RzSaIVO6ddGxgGc1DfKHQwudtUxgv6CvSgwlsZdOIJDASvhwcjMKeLfv2gAWFh8xyCtARkyNVbQiZVtC7gIORpBaawD1pJJsMogUuOpBb1RTQgDzLDEhCMFW5nU7Ew25XQzzimFfOBJ";
    //Conversion a horas = milisegundos*segundos*minutos*horas
    private final long expirationTime = 1000 * 60 * 60 * 24; //1 dia

    private Key getKey() {
        byte[] keyBytes = secret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date((System.currentTimeMillis() + expirationTime)))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
