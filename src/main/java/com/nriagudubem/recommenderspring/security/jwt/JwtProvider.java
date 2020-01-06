package com.nriagudubem.recommenderspring.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Log4j2
public class JwtProvider {

    @Value("${security.jwt.secretKey}")
    private String signingKey;

    @Value("${security.jwt.expiration}")
    private int tokenExpiration;

    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();
    }

    public String getUsernameFrom(String bearerToken) {
        return Jwts.parser().setSigningKey(signingKey).parseClaimsJwt(bearerToken).getBody().getSubject();
    }

    public boolean validateToken(String bearerToken) {
        try {
            Jwts.parser().setSigningKey(signingKey).parseClaimsJwt(bearerToken);
            return true;
        } catch (Exception ex) {
            log.error("exception has occurred, exception={}", ex.getMessage());
        }
        return false;
    }
}
