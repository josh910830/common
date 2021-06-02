package com.github.suloginscene.jwt;

import com.github.suloginscene.property.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;

import static com.github.suloginscene.jwt.Base64Utils.encoded;


@Component
public class JwtReader {

    private final JwtParser jwtParser;


    public JwtReader(SecurityProperties securityProperties) {
        jwtParser = Jwts.parser().setSigningKey(encoded(securityProperties.getSecret()));
    }


    public String getAudience(String jwt) throws InvalidJwtException {
        return getPayload(jwt).getAudience();
    }

    private Claims getPayload(String jwt) throws InvalidJwtException {
        try {
            return jwtParser.parseClaimsJws(jwt).getBody();
        } catch (JwtException e) {
            String message = selectMessageByClass(e);
            throw new InvalidJwtException(message);
        }
    }

    private String selectMessageByClass(JwtException e) {
        Class<? extends JwtException> eClass = e.getClass();
        if (eClass == ExpiredJwtException.class) return "Expired Jwt";
        if (eClass == SignatureException.class) return "Invalid Signature";
        return "Malformed Jwt";
    }

}
