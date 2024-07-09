package dev.example.kinect.auth;

import dev.example.kinect.model.Trainee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtils {
    private final String TOKEN_KEY = "mysecretkey!";
    private final int TOKEN_VALIDITY = 60*60*1000;
    private final String TOKEN_PREFIX = "Bearer ";
    private final String TOKEN_HEADER = "Authorization";
    private final JwtParser jwtParser;
    public JwtUtils(){
        this.jwtParser = Jwts.parser().setSigningKey(TOKEN_KEY);
    }
    // generate the token key
    public String createToken(Trainee trainee){
        Claims claims = Jwts.claims().setSubject(trainee.getEmail());
        claims.put("username", trainee.getUsername());
        Date tokenCreation  = new Date();
        Date tokenValidity = new Date(tokenCreation.getTime() + TimeUnit.MINUTES.toMillis(TOKEN_VALIDITY));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256,TOKEN_KEY)
                .compact();
    }

    public Claims parseJwt(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }
    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwt(token);
            } return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)){
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
    public boolean validateClaims(Claims claims) throws AuthenticationException {
        return claims.getExpiration().after(new Date());
    }

}
