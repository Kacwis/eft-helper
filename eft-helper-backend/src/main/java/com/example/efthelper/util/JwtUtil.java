package com.example.efthelper.util;

import com.example.efthelper.service.UserPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

@PropertySource({"classpath:application.yml"})
@Component
public class JwtUtil {

//    @Value("${app.jwtSecret}")
    private String jwtSecret = "sdaw8d213jndaksdow1o23dnaowdkasodwe2ndsda1323jjjg3wds";

//    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs = 84600000;


    public String generateJwtToken(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
                .compact();
    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret))).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret))).build().parseClaimsJws(authToken);
            return true;
        } catch(SignatureException e){
            System.out.println("Invalid JWT signature");
        } catch(MalformedJwtException e){
            System.out.println("Invalid JWT token");
        } catch(ExpiredJwtException e){
            System.out.println("JWT token has expired");
        } catch(UnsupportedJwtException e){
            System.out.println("JWT token is not supported");
        } catch(IllegalArgumentException e){
            System.out.println("JWT claims string is empty");
        }
        return false;
    }

    public String parseJwt(HttpServletRequest request) {
        var headerAuth = request.getHeader("Authorization");
        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
            return headerAuth.substring(7);
        }
        return null;
    }
}
