package com.omega.Proyecto.omega.Security;

import com.omega.Proyecto.omega.Model.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private final File credentials = new File("./credentials.txt");

    public String readSecretKey() throws IOException {
        FileReader fr = new FileReader(credentials);
        BufferedReader br = new BufferedReader(fr);
        try {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("key="))
                    return line.substring(line.indexOf("=") + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JwtService() {
    }

    public String extractUsername(String token) throws IOException {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) throws IOException {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public boolean isValid(String token, UserDetails employee) throws IOException {
        String username = extractUsername(token);
        return (username.equals(employee.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) throws IOException {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) throws IOException {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) throws IOException {
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generatorToken(Employee employee) throws IOException {
        String token = Jwts
                .builder()
                .subject(employee.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(getSigninKey())
                .compact();
        return token;
    }

    private SecretKey getSigninKey() throws IOException {
        byte[] keyBytes = Decoders.BASE64.decode(readSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
