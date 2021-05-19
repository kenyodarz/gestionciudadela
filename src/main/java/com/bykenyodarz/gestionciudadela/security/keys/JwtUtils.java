package com.bykenyodarz.gestionciudadela.security.keys;

import com.bykenyodarz.gestionciudadela.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${Gestion.app.jwtSecret}")
    private String jwtSecret;

    @Value("${Gestion.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            LOGGER.error("Firma del token invalida: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("Token invalido: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOGGER.error("Token ha expirado: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("Token alterado: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("Token esta vacío: {}", e.getMessage());
        }

        return false;
    }

}
